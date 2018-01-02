<!DOCTYPE html>
<html lang="en">
	<head>
		<?php
			define('php_include', true);
			include('header.php');
		?>
	</head>
		
	<body>

		<div id="main-menu">
			
			<ul class="menu-wrapper list-unstyled">
				<li>
					<a href="/">Home</a>
				</li>
			</ul>

			<button class="btn btn-toggle-menu">
				<i class="fa fa-bars" aria-hidden="true"></i>
			</button>

		</div>

		<main class="main-content">

		</main>

		<?php
			include('footer.php');
		?>

	</body>

</html>