# Mapbox utilities library for Android (Unofficial)

## Usage


## Features
#### Bubble icons
<img src="https://github.com/cammace/mapbox-utils-android/blob/master/screenshots/bubble_icons.png?raw=true" width="360">

A bubble icon is used to replace a marker icon. It is used to include snippets of information on
a marker itself such as price or address. They appear on a MapView looking like a miniature sized
infoWindow containing text of type String. This offers a huge advantage in that the markers are
already displaying vital information to users without them even having to click/tap the marker.
Since the bubble icons are generated on the fly you can customize things such as background color
and  text font color.

For example, to place a bubble icon with the string "HellowWorld", you do the following:
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


