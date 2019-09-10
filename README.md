# Affirm

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

 ![VideoWalkThrough](Flickr.gif)




