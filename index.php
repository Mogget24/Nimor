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

			<div id="planet"></div>

			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#planet-details">
				Launch demo modal
			</button>

		</main>

		<aside>
			
			<div class="modal modal-planet fade" id="planet-details">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Ciao Glorio</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<i class="fa fa-times" aria-hidden="true"></i>
							</button>
						</div>
						<div class="modal-body">
							<p>Guarda come mi gira il pianeta</p>
						</div>
						<div class="modal-footer">
							
						</div>
					</div>
				</div>
			</div>

		</aside>

		<?php
			include('footer.php');
		?>

	</body>

</html>