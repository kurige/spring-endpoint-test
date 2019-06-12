
import re
from operator import itemgetter
from collections import Counter

with open('gutenberg-100-0.txt', 'r') as content_file:
    content = content_file.read().lower()

words = re.findall(r"\w+", content)
count = Counter(words)
percentages = {x: float(y) / len(words) for x, y in count.items()}

print 'word,count,frequency'
for word, pct in percentages.iteritems():
    print '%s,%s,%s' % (word, count[word], pct)
