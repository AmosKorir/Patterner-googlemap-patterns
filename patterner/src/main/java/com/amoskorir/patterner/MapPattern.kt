package com.amoskorir.patterner

import android.content.Context
import android.graphics.*
import com.google.android.gms.maps.model.*
import java.util.*

class GMapPattern {
	companion object {
		
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
		): Bitmap {
			val latLongs = polygonOptions.points
			val bounds = getPolygonBounds(latLongs)
			val bitmap = BitmapFactory.decodeResource(context.resources, drawable)
			val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
			val bitmapPolygon = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
			val polygonCanvas = Canvas(bitmapPolygon)
			Canvas(mutableBitmap)
			
			val paint = Paint()
			paint.strokeWidth = 9f
			paint.isAntiAlias = true
			
			val path = Path()
			val points = ArrayList<Point>()
			val boundHeight: Double = bounds.northeast.latitude - bounds.southwest.latitude
			val boundWidth: Double = bounds.northeast.longitude - bounds.southwest.longitude
			
			for (latLng in latLongs) {
				val screenPoint = Point()
				screenPoint.x =
					((width * (latLng.longitude - bounds.southwest.longitude) / boundWidth).toInt())
				
				screenPoint.y =
					height - (height * (latLng.latitude - bounds.southwest.latitude) / boundHeight)
						.toInt()
				points.add(screenPoint)
			}
			
			path.moveTo(points[0].x.toFloat(), points[0].y.toFloat())
			
			for (i in 1 until points.size) {
				path.lineTo(points[i].x.toFloat(), points[i].y.toFloat())
			}
			
			path.lineTo(points[0].x.toFloat(), points[0].y.toFloat())
			
			polygonCanvas.drawPath(path, paint)
			paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
			polygonCanvas.drawBitmap(mutableBitmap, 0f, 0f, paint)
			return bitmapPolygon
		}
		
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
		): GroundOverlayOptions {
			val groundPolygonBitmap = drawPolygonBitmap(
				context, polygonOptions, width, height, drawable
			)
			val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(groundPolygonBitmap)
			val groundOverlayOptions = GroundOverlayOptions()
			groundOverlayOptions.image(bitmapDescriptor)
			groundOverlayOptions.positionFromBounds(getPolygonBounds(polygonOptions.points))
			return groundOverlayOptions
		}
		
		private fun getPolygonBounds(polygon: List<LatLng>): LatLngBounds {
			val builder = LatLngBounds.Builder()
			for (i in polygon.indices) {
				builder.include(polygon[i])
			}
			return builder.build()
		}
	}
}