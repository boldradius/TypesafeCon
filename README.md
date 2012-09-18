Scala1
======
Repository for the mobile application for the **JavaOne** conference on **September 2012**.


Running Scala1
--------------
To install and run Scala1 you need to do the following:

1. Clone this repository
2. Install Postgres (If you prefer a different DB make sure to change the application.conf accordingly)
3. Create a DB user for scala1
4. Create a plain text file conf/private.conf with the following data:



    # DB credentials
    db.default.user=YourDBUser
    db.default.password=YourDBUserPassword
	
	# The secret key is used to secure cryptographics functions.
	application.secret=AnyStringOfLength16xNWillDo12345
	
	# Pusher configuration 
	# Keep Pusher disabled or get a free account and enter your credentials here
	pusher.enabled=false
	#pusher.appId=YourAppId
	#pusher.key=YourKey
	#pusher.secret=YourSecret
	
	# Internal security configuration
	security.token=AnyValueWillDo

5. Make sure ImageMagick is installed on your system by running `convert`. If it is not, use your preferred [installation](http://www.imagemagick.org/script/binary-releases.php) [method](https://help.ubuntu.com/community/ImageMagick).  
6. To verify the installation, cd to the project root and run sbt test. All tests should run successfully.
7. Hack away!

If you use Eclipse or Idea, you'll have to run the proper `sbt` target to generate project files, as those are not checked into the repository.

Playground
----------
TODO

Contributing to Scala1
----------------------
TODO

