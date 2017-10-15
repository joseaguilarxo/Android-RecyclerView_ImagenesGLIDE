# Android-RecyclerView_ImagenesGLIDE
Llenar un RecyclerView con datos consumidos de una WebServices utilizando RETROFIT y Glide para las imagenes
LLenar un RecyclerView con datos de una webservice consumida por Retrofit y Glide

Para este ejemplo estaremos consumiendo la webservice un "pokeapi.co"


Paso 1: implementar dependencias

compile 'com.github.bumptech.glide:glide:3.7.0'
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
compile 'com.android.support:design:25.3.1'
compile 'com.android.support:cardview-v7:25.3.1'
compile 'com.android.support:recyclerview-v7:25.3.1'

Paso 2: Dar permiso de uso de internet
<uses-permission android:name="android.permission.INTERNET" />

Paso 3: La programacion sera de esta manera

	- a) Programaremos el consumo del webservices rest por Retrofit
		a.1) Creacion y programacion de la clase
		a.2) Creacion y programacion del control respuesta	
		a.3) Creacion y programacion del ApiService
		a.4) Programacion del MainActivity
	- b) Construiremos el RecyclerView con su adapter y mostraremos los datos en el
		Creacion del Adapter
	- c) Integraremos las imagenes utilizando glide

Toda esta logica estara documentada dentro de la aplicacion, para poder diferenciar los temas
se documentara en
" Paso 1 Retrofit : creacion del metodo " 
" Paso 1 RecyClerView : creacion de Adapter "
" Paso 1 Glide : consumiendo url " 

Asi de esa forma distinguiremos que paso pertenece a que paso.
