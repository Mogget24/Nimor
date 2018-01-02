<?php
	
	if(!defined('php_include')){
		die("Direct access not permitted");
	}
	else{
		?>

		<title> 
			<?php 
				if(defined('page_title')){
					echo $title;
				}
				else {
					echo "Nimor";
				}
			?>
		</title>

		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2, user-scalable=1"/>
		<meta charset="UTF-8">
		<meta name="description" content="">
		<meta name="keywords" content="">
		<link rel="stylesheet" type="text/css" href="css/reset.css">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

		<!--<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">-->
		<!--<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">-->

		<!--

		<script>
			(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
			m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
			})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

			ga('create', 'UA-47580759-6', 'auto');
			ga('send', 'pageview');
		</script>

		-->

		<?php
	}

?>

