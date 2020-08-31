To run the app, simply run run-application.sh.
PS: You need to manually delete the sqlite db after closing the app or otherwise data seeder is going to insert 
duplicate entries next time you run the app. 

Tech stack:
- spring boot
- java8
- sqlite

Requirements:
- endpoints:
	- book PYO
		- accept a post request with
			- advertiserId
			- breakId
			- 
	- cancel existing PYO (check if it exists)
	- list PYO requests 


Entities:
Defined in reqs
+-----------+
|rating		|
+-----------+
|BreakId	|
|Demographic|
|TVR		|
+-----------+

+-----------+
|break 		|
+-----------+
|BreakId	|
|Date		|
|Time		|
|Duration	|
+-----------+

+---------------+
|campaign 		|
+---------------+
|CampaignId		|
|AdvertiserId	|
|SpotLength		|
|Demographic	|
|TargetTVR		|
|PYOPercentage	|
+---------------+

Extra entities:
+-------------------+
|demographic profile|
+-------------------+
|DpId				|
|Gender				|
|Age				|
|?Location			|
+-------------------+

+-------------------+
|advertiser 		|
+-------------------+
|AdvertiserId		|
|Name				|
+-------------------+

+---------------+
|PYO 			|
+---------------+
|PYOId			|
|BreakId		|
|CampaignId		|
+---------------+

Terminology:
- TVR: TV Rating, i.e. percentage of target audience

Use cases:
- multiple advertisers could opt to book a specific break for their ad campaigns