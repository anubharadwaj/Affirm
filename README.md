# Affirm

Summary
* Spend up to 3 hours building an app that displays a photo feed, which will be populated with photos fetched from the Flickr API based on a user query.

Requirements
* Create an app that has a search bar and a photo feed.
* The search bar should query Flickr’s API to fetch photos based on user input. 
* The photo feed should display the results of the Flickr API fetch.
* The feed should allow the user to scroll endlessly. When the user reaches the bottom of the feed, the app should load another page of photos from the Flickr API. This should load seamlessly in the style of Facebook’s and Twitter’s feeds, and it should not require pull-to-refresh.
* The app should follow the standard interface paradigms of its platform. 
* Submit a write-up based on the questions below along with your code. Don't count the time spent on this against your 3 hours.
 Guidelines
* Build the app with an eye toward architecture and code quality, as though you were working with a large team and eventually planning to ship this code to the App Store and you and your team were required to maintain it over the long-term. Try not to “hack” it together. In summary, prioritize craftsmanship.
* Feel free to make judgements about what to prioritize for the sake of this project. Make smart trade-offs, as you likely do as part of your job now. 
* You don’t have to handle every edge case. Feel free to use TODOs to denote small cleanup tasks.
* Use frameworks and libraries that you know. Don’t feel like you need to learn something new to build this. Showcase your existing skillset. 
* If you are familiar with git, please use it to track your progress with useful commit messages.
* Plan to spend up to 3 hours working on this. If you finish and have remaining time, be creative about making improvements to your app. Please document what these are in the write-up.

Write-Up
* How long did you spend working on the problem? What did you find to be the most difficult part?
* What trade-offs did you make? What did you choose to spend time on, and what did you decide to not do for the sake of completing the project? 
* If you finished with extra time, what improvements did you make that go above and beyond the requirements? 

Flickr API
==========
Here are the links for the relevant part of the Flickr API that you will be using:

https://www.flickr.com/services/api/flickr.photos.search.html
https://www.flickr.com/services/api/misc.urls.html

The important part of the above pages are that to make a search request you can use the URL:

https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=675894853ae8ec6c242fa4c077bcf4a0&text={query}&extras=url_s&format=json&nojsoncallback=1

An example search for "dogs" would be:

https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=675894853ae8ec6c242fa4c077bcf4a0&text=dogs&extras=url_s&format=json&nojsoncallback=1

Here is an example of one of the photo objects you will be returned from that search API call:

{
   "farm": 1,
   "height_s": "240",
   "id": "33094387050",
   "isfamily": 0,
   "isfriend": 0,
   "ispublic": 1,
   "owner": "29314320@N07",
   "secret": "89019909cc",
   "server": "667",
   "title": "Stanley T. 3-16-2017",
   "url_s": "https://farm1.staticflickr.com/667/33094387050_89019909cc_m.jpg",
   "width_s": "180"
}

Flickr Search App
Implemented features:
- User can search for images by typing search query in searchbox
- User can infinitely scroll through the images
- Offline mode

Technical Details:
- Min SDK Version 5.1/API level 22
- The app uses MVVM design pattern
- It also uses the new Android architecture components:(https://developer.android.com/jetpack/docs/guide)
  - ViewModel
  - Room
  - LiveData
  - Paging
- Broadcast receiver to detect network state
- Repository class to switch between the 2 data sources( from network vs DB)
- Project was migrated to Androidx

Future improvements:
  - Handle configuration changes and not locking screen rotation
  - Add a Snackbar to show network state change instead of a Toast message
  - Show internet connected only after it was disconnected previously

 ![VideoWalkThrough](Flickr.gif)




