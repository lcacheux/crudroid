Crudroid
========

Quickly create activities to manage your data in your activity. CRUD is an acronym for Create Read
Update Delete, which is all the basic functionality you need to manipulate objects from a database
or another source.

Crudroid provide a base activity with these features to have a test application, a debug mode or
even part of a real production app ready in a few minutes. Using Android Architecture Components'
ViewModel, any kind of data source can be plugged to your activity.

Requirements
------------

Minimum requirement for this library is API level 19 (Android 4.4 KitKat).

Crudroid relies on some libraries from Android Jetpack, so they will be included in your project as
dependencies :
* v7 Support Libraries
* Design Support Library
* Lifecycle's LiveData and ViewModel

Usage
-----

Crudroid is available on Maven Central, so you can add it as a dependency. In your root build.gradle
file, add mavenCentral():
```groovy
allprojects {
    repositories {
        mavenCentral()
    }
}
```

In your module build.gradle file, add crudroid:
```groovy
dependencies {
    implementation 'net.cacheux.crudroid:crudroid:0.1.0'
}
```

Declare a new activity in your application which will inherit __CrudActivity<T>__ with T as the
model class you want to manage. You'll have to override three abstract methods :
* _getListAdapter_: It must provide an instance of __CrudListAdapter<T>__, which is a
__RecyclerView.Adapter__.
* _getEditViewBinder_: Must provide an instance of __EditViewBinder<T>__, an interface which provide
a layout for the edit popup and methods to match its views with the T values.
* _getViewModelClass_: Return the class for the __CrudViewModel<T>__ implementation, which is a
__AndroidViewModel__. It must provide a __CrudProvider<T>__ with implementations for the
create/read/update/delete methods.

Using simple implementations
----------------------------

For simple models with a single field, simple implementations of EditViewBinder and CrudListAdapter
are provided. Your model class must implement the __SimpleItem__ interface with a method to set the
value from a String.