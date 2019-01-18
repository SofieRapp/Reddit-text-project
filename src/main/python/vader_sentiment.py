import pandas as pd
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer


analyzer = SentimentIntensityAnalyzer()

textlines = [line.rstrip('\n')
             for line in open('..\\resources\\leagueoflegends.txt', encoding='utf-8')]

title = []
compound = []
positive = []
negative = []

for i in range(0, len(textlines)):
    scores = analyzer.polarity_scores(textlines[i])
    # Removes post that doesn't have a sentiment
    if scores['compound'] != 0:
        title.append(textlines[i])
        compound.append(scores['compound'])
        positive.append(scores['pos'])
        negative.append(scores['neg'])


leauge_df = pd.DataFrame({'Title' : title, 'positive' : positive, 'negative' : negative, 'compound' : compound})


print(leauge_df)
