<?php
$active="home";
include('../includes/header.php');
?>



        <!--Content Section-->
        <section id="content">
            <div class="container">
					 <div class="card">
                        <div class="card-header">
                            <h2>New Authenticator<small>These Admins can validate/View Residents Details.</small></h2>
                        </div>
						
                        <div class="card-body card-padding">
						
                           <form action="addnewapi.php" method="post" enctype="multipart/form-data">
						   <div class="form-group">
                                <div class="fg-line">
						   <input type="text" name="name" class="form-control input-sm" placeholder="Authority Name" required>
                                </div>
                            </div>
							
							<div class="form-group">
                                <div class="fg-line">
						   <input type="text" name="username" class="form-control input-sm" placeholder="UserName" required>
                                </div>
                            </div>
							
							<div class="form-group">
                                <div class="fg-line">
						   <input type="password" name="password" class="form-control input-sm" placeholder="Password" required>
                                </div>
                            </div>
						   
						   <div class="form-group">
                                        <div class="fg-line">
                                            <div class="select">
                                                <select class="form-control" name="typeselect" required>
                                                    <option value="Govt.">Government Authenticator</option>
                                                    <option value="Pvt.">Private Authenticator</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                            
                            
							<br/><br/>
							<div class="toggle-switch" data-ts-color="lime">
                                        <label for="ts6" class="ts-label">Enable / Disable this Authenticator</label>
                                        <input name="status" id="ts6" type="checkbox" hidden="hidden">
                                        <label for="ts6" class="ts-helper"></label>
                                    </div>
							<br/>
							<br/>
							<button type="submit" class="btn btn-primary btn-sm m-t-10 waves-effect" name="add_auth">Add</button>
                            <br/>
							<br/>
                            
							</form>
						</div>
						</div>

			 </div>
        </section>

   
        
      <?php
include('../includes/footer.php');
?>