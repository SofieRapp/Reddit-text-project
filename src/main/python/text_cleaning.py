import contractions
import nltk
from nltk.stem import WordNetLemmatizer

engTexts = [u'It is a truth universally acknowledged, that a single man in possession of a good fortune, must be in want of a wife.',
            u'Once an angry man dragged his father along the ground through his own orchard. "Stop!" cried the groaning old man at last, "Stop! I did not drag my father beyond this tree."',
            u'He was an old man who fished alone in a skiff in the Gulf Stream and he had gone eighty-four days now without taking a fish.',
            u'You don\'t know about me without you have read a book by the name of The Adventures of Tom Sawyer; but that ain\'t no matter.']

#Remove contractions
for i in range(0, len(engTexts)):
    engTexts[i] = contractions.expandContractions(engTexts[i])

#Lemmatize words
wordnet_lemmatizer = WordNetLemmatizer()
for sent in engTexts:
    for word in nltk.word_tokenize(sent):
        wordnet_lemmatizer.lemmatize(word)

print(engTexts[3])

#Remove numbers (?)


#Remove stopwords, nltk stopword corpra

#Tokenize and create freqDist
