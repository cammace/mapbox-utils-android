# Mapbox utilities library for Android (Unofficial)

## Usage


## Features
#### Bubble icons
<p align="center">
<img src="https://github.com/cammace/mapbox-utils-android/blob/master/screenshots/bubble_icons.png?raw=true" width="360">
</p>

A bubble icon is used to replace a marker icon. It is used to include snippets of information on
a marker itself such as price or address. They appear on a MapView looking like a miniature sized
infoWindow containing text of type String. This offers a huge advantage in that the markers are
already displaying vital information to users without them even having to click/tap the marker.
Since the bubble icons are generated on the fly you can customize things such as background color
and  text font color.

For example, to place a bubble icon with the string "HelloWorld", you do the following:
```java
        // To place a BubbleIcon marker
        BubbleIconFactory bubbleIcon = new BubbleIconFactory(MainActivity.this);
        IconFactory iconFactory = IconFactory.getInstance(this);

        MarkerOptions markerOptions = new MarkerOptions()
                .icon(iconFactory.fromBitmap(bubbleIcon.makeIcon("HelloWorld")))
                .position(new LatLng(41.885, -87.679));
        mapView.addMarker(markerOptions);
```
#### Calculate distance, heading, points between, and more
##### Midpoint
<p align="center">
<img src="https://github.com/cammace/mapbox-utils-android/blob/master/screenshots/midpoint.png?raw=true" width="360">
</p>

To find the midpoint between any two given latitude/longitude points just call the method like so:

```java
        LatLng from = new LatLng(41.890009, -87.762992);
        LatLng to = new LatLng(41.913046, -87.639444);
        LatLng midpoint = MathUtil.midPoint(from, to);
```

##### Intermediate points
<p align="center">
<img src="https://github.com/cammace/mapbox-utils-android/blob/master/screenshots/intermediate_points.png?raw=true" width="360">
</p>

Gives a list of intermediate points between two given latitude/longitude points. How many points is
determined by the double value smoothness. The larger the value the more points you'll received between
the original two given points. When performing this calculation, think of it as your breaking up a
line between the two given points into n amount of sections. This n amount of sections is determined
by the smoothness parameter. It's important to note that the last point in the list is the same as
the "to" point. 

```java
        LatLng from = new LatLng(41.890009, -87.762992);
        LatLng to = new LatLng(41.913046, -87.639444);
        List<LatLng> betweenPoints = MathUtil.pointsBetween(from, to, 10);
```

