# Affirm

Flickr Search App
Implemented features:
- User can search for images by typing search query in searchbox
- User can infinitely scroll through the images
- Offline mode

Technical Details:
- The app used MVVM design pattern
- It also uses the new Android architecture components:(https://developer.android.com/jetpack/docs/guide)
  - ViewModel
  - Room
  - Paging
- Broadcast receiver to detect network state
- Repository class to switch between the 2 data sources( from network vs DB)
