<?php
$active="home";
include('../includes/header.php');
?>



        <!--Content Section-->
        <section id="content">
            <div class="container">
					 <div class="card">
                        <div class="card-header">
                            <h2>New Document Type<small>If it is a Schema based document, add necessasry schema first.</small></h2>
                        </div>
						
                        <div class="card-body card-padding">
						
                           <form action="addnewdocapi.php" method="post" enctype="multipart/form-data">
						   <div class="form-group">
                                <div class="fg-line">
						   <input type="text" name="name" class="form-control input-sm" placeholder="Name" required>
                                </div>
                            </div>
							<br><br/>
						   <div class="form-group">
										<p>Please Select Department</p>
                                        <div class="fg-line">
                                            <div class="select">
                                                <select class="form-control" name="dept" required>	
									
	<?php
$query=mysqli_query($con,"SELECT * FROM v_auth");
while($row=mysqli_fetch_array($query))
{
	?>

                                        <option value="<?php echo $categoryname=$row["id"];?>"><?php echo $categoryname=$row["name"];?></option>
											<?php
}
?>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
									
									
									
									<br><br/>
								<div class="form-group">
								
										<p>Please Select Minimum Required Documents. If its not on the list please add this first.</p>
								
									<?php
$query2=mysqli_query($con,"SELECT * FROM document_list");
while($row2=mysqli_fetch_array($query2))
{
	?>
								
                                <label class="checkbox checkbox-inline m-b-15">
                                <input type="checkbox" name="needed[]" value="<?php echo $row2["id"];?>">
                                <i class="input-helper"></i>
                                <?php echo $categoryname=$row2["name"];?>
								</label>
								
																			<?php
}
?>
								</div>
                            
                            
							<br/><br/>
							<div class="toggle-switch" data-ts-color="lime">
                                        <label for="ts6" class="ts-label">Verification Approval Not Needed / Needed</label>
                                        <input name="status" id="ts6" type="checkbox" hidden="hidden">
                                        <label for="ts6" class="ts-helper"></label>
                                    </div>
							<br/>
							<br/>
							<button type="submit" class="btn btn-primary btn-sm m-t-10 waves-effect" name="add_doc">Add</button>
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