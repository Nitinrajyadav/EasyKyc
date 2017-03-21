<?php
$active="home";
include('../includes/header.php');
?>


            <section id="content">
                <div class="container">
                    <div class="card">
                        <div class="card-header">
                            <h2>Available Requests<small>Manage all your Requests here</small></h2>
                        </div>
						
                             <div class="table-responsive">
                            <table id="data-table-basic" class="table table-striped">
                                <thead>
                                    <tr>
										<th>Req Id</th>
                                        <th>Doc Id</th>
                                        <th>User Id</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
							$myid= $_SESSION['id'];
							$main=mysqli_query($con, "SELECT * FROM v_auth WHERE userid='$myid'");
									while ($rowmain=mysqli_fetch_array($main)){
										 $adminId=$rowmain["id"];
										
										
										$main2=mysqli_query($con, "SELECT * FROM `document_list` WHERE v_auth_id=$adminId");
										while ($rowmain2=mysqli_fetch_array($main2)){
										 $xyz=$rowmain2["id"];

										
										
										$main3=mysqli_query($con, "SELECT * FROM user_doc WHERE doc_id=$xyz");
										while ($rowmain3=mysqli_fetch_array($main3)){
											
											?>
											
										<tr>
										 <td><p><?php echo $reqId=$rowmain3["id"];?></p></td>
										 <td><p><?php echo $reqId=$rowmain3["doc_id"];?></p></td>
										 <td><p><?php echo $reqId=$rowmain3["user_id"];?></p></td>
										 <td>
										 
										 <?php
										$status=$rowmain3["status"];
										if($status=='100'){
										?>
										<a href="changeStatus.php?id=101&reqtId=<?php echo $rowmain3["id"];?>"><button class="btn palette-Orange bg btn-xs waves-effect">Approve</button></a>
										<a href="changeStatus.php?id=102&reqtId=<?php echo $rowmain3["id"];?>"><button class="btn palette-Orange bg btn-xs waves-effect">Cancel</button></a>
										
										<?php
										}
										else if($status=='102'){
										?>
										
										<a href="javascript:void(0)"><button class="btn palette-Red bg btn-xs waves-effect">Cancelled</button></a>
										<?php
										}
										elseif($status=='101'){
											?>
										<a href="javascript:void(0)"><button class="btn palette-Light-Green bg btn-xs waves-effect">Verified</button></a>
										<?php
										}
										?>
										 
										 </td>
										</tr>
										<?php
										}
										}
										}
								
								?>	
                                </tbody>
                            </table>
                        </div>
                  </div>

                </div>
            </section>
 <?php
include('../includes/footer.php');
?>