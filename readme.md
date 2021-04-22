# Patterner-googlemap-patterns

**Overlay Patterns on android google map**


[![](https://jitpack.io/v/AmosKorir/Patterner-googlemap-patterns.svg)](https://jitpack.io/#AmosKorir/Patterner-googlemap-patterns)

Patterner is an android library, that draws line patterns on google map programmatically, it supports irregular shaped-polygons.

<table>
<tr>
<td>
<img  width="200" height="400" src="https://github.com/AmosKorir/Patterner-googlemap-patterns/art/s.jpg"/>
</td>
</tr>
</table>

**How to use**

Add dependency to the Gradle:

```java
  implementation 'com.github.AmosKorir:Patterner-googlemap-patterns:Tag'
 ```

 **Example usage of the MapPatterner**

 ```kotlin
  //overlay
  val groundOverlay = GMapPattern.getBitmapPolygonGround(
             this,
             polygonOptions,
             500,
             500,
             R.drawable.example_pattern
         )
 
    map.addGroundOverlay(groundOverlay)
 
```

 **getBitmapPolygonGround** returns a shaped-bitmap image ovelay of the polygon supplied.

  ```kotlin
  

   /**
   * @param context , this is the view context, it required for creating bitmap
   * @param polygonOptions, this provide list of latLongs that forms the polygon
   * @param width , Integer width of the required pattern
   * @param height , Integer width of the required pattern
   * @param drawable image resource ID
   * @return Ground Overlay
   */
  
  fun getBitmapPolygonGround(
   context: Context,
   polygonOptions: PolygonOptions,
   width: Int,
   height: Int,
   drawable: Int
  ): GroundOverlayOptions

```

 **drawPolygonBitmap** returns a shaped-bitmap of the polygon supplied.




```kotlin

	/**
   * @param context , this is the view context, it required for creating bitmap
   * @param polygonOptions, this provide list of latLongs that forms the polygon
   * @param width , Integer width of the required pattern
   * @param height , Integer width of the required pattern
   * @param drawable image resource ID
   * @return Bitmap
   */
  fun drawPolygonBitmap(
   context: Context,
   polygonOptions: PolygonOptions,
   width: Int,
   height: Int,
   drawable: Int
  ): Bitmap

```
**To run this example you need to set the you map api key in local.properties file**
add below and change the value to you api key


```json
api_map_key="AIzaSyCz75O....."
```
