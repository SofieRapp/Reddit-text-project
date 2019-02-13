import pandas as pd
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer


analyzer = SentimentIntensityAnalyzer()

text_lol = [line.rstrip('\n') for line in open('..\\resources\\leagueoflegends.txt', encoding='utf-8')]
text_ow = [line.rstrip('\n') for line in open('..\\resources\\overwatch.txt', encoding='utf-8')]


def create_scores(text_lines):
    title = []
    compound = []
    positive = []
    negative = []

    for i in range(0, len(text_lines)):
        if 'Post-Match Discussion' in text_lines[i]:
            continue
        scores = analyzer.polarity_scores(text_lines[i])
        # Removes post that doesn't have a sentiment
        if scores['compound'] != 0:
            title.append(text_lines[i])
            compound.append(scores['compound'])
            positive.append(scores['pos'])
            negative.append(scores['neg'])

    return pd.DataFrame({'Title' : title, 'positive' : positive, 'negative' : negative, 'compound' : compound})


league_df = create_scores(text_lol)
ow_df = create_scores(text_ow)

print(league_df)
