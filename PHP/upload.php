<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	$nome = $_POST['nome'];
	$senha = $_POST['senha'];
	require_once('dbConnect.php');

	$sql = "SELECT * FROM usuarios WHERE senha='$senha' AND nome='$nome'";
	if($res = mysqli_query($con,$sql)){
		$linhas=mysqli_num_rows($res);
		
		if($linhas>= 1 ){
			//$json = "Logado com sucesso!";
			$json = 1;
			echo json_encode($json);	
		}else{
			//$json= "Usuario o senha invalidos!";
			$json= 0;
			echo json_encode($json);
		}
	}

}else{
	$json= "Erro ao receber os dados";
	echo json_encode($json);
}
?>