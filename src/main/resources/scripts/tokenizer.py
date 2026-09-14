import io
import re
import sys

import pyphen


class UniversalSyllableTokenizer:
    def __init__(self):
        # only useful if python process is kept alive inbetween queries
        self._dictionaries = {}
        # Matches a run of word characters (letters/digits/underscore, unicode-aware)
        # OR a single non-space, non-word character (punctuation/symbols) as its own chunk.
        self._word_or_punct = re.compile(r'\w+|[^\w\s]', re.UNICODE)

    def _get_dic(self, lang):
        if lang not in self._dictionaries:
            self._dictionaries[lang] = pyphen.Pyphen(lang=lang)
        return self._dictionaries[lang]

    def tokenize_word(self, word, lang):
        dic = self._get_dic(lang)
        if lang.startswith('ru'):
            # lib doesn't parse "ё" correctly
            normalized = word.replace('ё', 'е').replace('Ё', 'Е')
            hyphenated = dic.inserted(normalized)
            result = []
            orig_idx = 0
            for ch in hyphenated:
                if ch == '-':
                    result.append(ch)
                else:
                    result.append(word[orig_idx])
                    orig_idx += 1
            hyphenated = ''.join(result)
        else:
            hyphenated = dic.inserted(word)
        return hyphenated.split('-')

    def tokenize_line(self, line, lang):
        """
        Splits a line into word-groups. Each group is a list of tokens:
        - alphabetic words are syllabified into multiple tokens
        - digit runs are a single token
        - punctuation attaches onto the last token of the current group
        Returns a list of groups, each group being a list of tokens (one group per <EOW>).
        """
        groups = []
        current = None

        for chunk in self._word_or_punct.findall(line):
            if chunk.isalpha():
                current = self.tokenize_word(chunk, lang)
                groups.append(current)
            elif chunk.isdigit():
                current = [chunk]
                groups.append(current)
            else:
                if current:
                    current[-1] += chunk
                else:
                    current = [chunk]
                    groups.append(current)

        return groups


def main():
    # Force UTF-8 regardless of the parent process's locale/environment
    stdin = io.TextIOWrapper(sys.stdin.buffer, encoding="utf-8", newline="")
    stdout = io.TextIOWrapper(sys.stdout.buffer, encoding="utf-8", newline="\n")

    language = sys.argv[1]
    ust = UniversalSyllableTokenizer()

    for line in stdin:
        line = line.rstrip("\r\n")  # strip both \n and stray \r from CRLF
        stdout.write("<BOL>\n")
        for group in ust.tokenize_line(line, language):
            for token in group:
                stdout.write(token + "\n")
            stdout.write("<EOW>\n")
        stdout.write("<EOL>\n")

    stdout.flush()


if __name__ == "__main__":
    main()
