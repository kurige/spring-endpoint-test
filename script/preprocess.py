
import re
from operator import itemgetter
from collections import Counter

with open('freq_small.txt', 'r') as content_file:
    content = content_file.read().lower()

words = re.findall(r"\w+", content)
count = Counter(words).items()
percentages = {x: float(y) / len(words) for x, y in count}

for name, pct in percentages.iteritems():
    print '%s - %s' % (name, pct)
