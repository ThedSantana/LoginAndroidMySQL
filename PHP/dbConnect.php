<?php 

	define('HOST','localhost');
	define('USER','nomeUsuario');
	define('PASS','senha');
	define('DB','nomeBanco');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('unable to connect to db');