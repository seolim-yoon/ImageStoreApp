# ImageStoreApp

* 사용 기술
  + Kotlin
  + Recyclerview
  + ViewPager2
  + MVVM
  + Databinding
  + LiveData
  + ViewModel
  + Room
  + Paging3
  + Retrofit2
  + OkHttp
  + RxJava2
  + Glide
  + Hilt
  + Kakao 검색 API 사용 
  
  
  #
  

* 프로젝트 구조

<img src="https://user-images.githubusercontent.com/73940842/134798950-9da34abf-5c35-4082-a9b9-2db71dbaac6e.PNG" width="320" height="360">


#


* 기능

1. 검색 화면 (SearchFragment)  
    + 검색어 입력 후 이미지 + 동영상 검색
    + 하트 아이콘 클릭 시 보관함으로 이동
    + 검색 기록 저장 및 저장된 기록 으로 재검색 기능
    
2. 보관함 화면 (StoreFragment)
    + 검색 화면에서 하트 아이콘이 클릭 된 리스트 보여줌


#


* 결과 화면

<img src="https://user-images.githubusercontent.com/73940842/134799720-c84f0b21-7a9c-45e9-b342-1404d79c12ca.jpg" width="250" height="450"> <img src="https://user-images.githubusercontent.com/73940842/134799718-2ba02446-cfb2-41c1-9a7e-e70bbed83057.jpg" width="250" height="450"> <img src="https://user-images.githubusercontent.com/73940842/134799716-8e3d30c9-e065-4c5c-81a5-520895643743.jpg" width="250" height="450"> <img src="https://user-images.githubusercontent.com/73940842/134799714-f5d70f7a-e8c4-45d6-a2b0-fb18a39fa9e4.jpg" width="250" height="450"> <img src="https://user-images.githubusercontent.com/73940842/134799722-b4c83337-16cc-44f9-9b80-152a3bc751b2.jpg" width="250" height="450">


#


* 사용 API
1. 이미지 검색
```
    @GET("v2/search/image")
    fun getImageList(
        @Header("Authorization") key: String,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<ImageResult>
```


2. 동영상 검색
```
    @GET("v2/search/vclip")
    fun getVideoList(
        @Header("Authorization") key: String,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<VideoResult>
```
