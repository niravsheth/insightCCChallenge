# insightCodingChallenge
PROBLEM:
A newspaper editor was researching immigration data trends on H1B(H-1B, H-1B1, E-3) visa application processing over the past years, trying to identify the occupations and states with the most number of approved H1B visas. She has found statistics available from the US Department of Labor and its Office of Foreign Labor Certification Performance Data. But while there are ready-made reports for 2018 and 2017, the site doesn’t have them for past years.

As a data engineer, you are asked to create a mechanism to analyze past years data, specificially calculate two metrics: Top 10 Occupations and Top 10 States for certified visa applications.

Your code should be modular and reusable for future. If the newspaper gets data for the year 2019 (with the assumption that the necessary data to calculate the metrics are available) and puts it in the input directory, running the run.sh script should produce the results in the output folder without needing to change the code.



Appoarch:
Have coded in java langauge.
Created 2 treemaps in java one for the occupation aggregator and one for the states for cerified h1b applicants.
Sorted the most certified h1b applicants via the occupation and also via state and returned the top results in two seperate file inside the output folder.


Run Instruction:
Main class name is h1b_counting.
The file is in the src folder.
