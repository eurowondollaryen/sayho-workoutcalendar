			/* SET MODAL WHEN SELECT CALENDAR CELL */
			function addWorkout(year, month, date) {
				
				if(month < 10) month = "0" + month;
				if(date < 10) date = "0" + date;
				var ymd = year + "/" + month + "/" + date;
				/* SET TITLE OF BOTH */
				document.getElementById("addWorkoutModalTitle").innerHTML = "<div>운동 기록 추가하기</div><div id='selectedModalYmd'>" + ymd + "</div>"; 
				document.getElementById("repsInputArea").innerHTML = "";
				
				document.getElementById("showWorkoutModalTitle").innerHTML = "<div>운동 기록 조회</div><div id='selectedModalYmdShow'>" + ymd + "</div>";
				document.getElementById("showWorkoutModalBody").innerHTML = "";
				
				/* SET sayho-modal-show */
				var todayWorkoutData = [];
				for(var i = 0; i < global_workout_data.length; ++i) {
					if(global_workout_data[i]["wo_dt"] == ymd.replace('/','').replace('/','')) {
						todayWorkoutData.push(global_workout_data[i]);
						var addTarget = document.getElementById("showWorkoutModalBody");
						var addDiv = document.createElement("DIV");
						var addCheckbox = document.createElement("input");
						addDiv.classList.add("sayho-modal-show-body");
						addDiv.appendChild(addCheckbox);
						addDiv.append(global_workout_data[i]["part_main_nm"] + "/"
						+ global_workout_data[i]["wo_nm_kor"] + "/"
						+ global_workout_data[i]["reps"]);
						addCheckbox.setAttribute("type", "checkbox");
						addCheckbox.classList.add("checkbox-workout-record");
						addCheckbox.setAttribute("value", global_workout_data[i]["seq"]);
						
						/* NOT DESIGNED YET - 20200720 */
						addTarget.appendChild(addDiv);
					}
				}
				
				/* SET BOTH */
				if(todayWorkoutData.length > 0) {
					document.getElementById("sayho-modal-add").setAttribute("style", "display:none;");
					document.getElementById("sayho-modal-show").setAttribute("style", "display:block;");
				} else {
					document.getElementById("sayho-modal-add").setAttribute("style", "display:block;");
					document.getElementById("sayho-modal-show").setAttribute("style", "display:none;");
				}
				
			}
			
			/* DELETE WORKOUT RECORD ON sayho-show-modal*/
			function deleteWorkoutRecord() {
				var checkboxes = document.getElementsByClassName("checkbox-workout-record");
				var requestData = {
					seqList: ""
				};
				for(var i = 0; i < checkboxes.length; ++i) {
					if(checkboxes[i].checked) {
						if(requestData["seqList"].length <= 0) requestData["seqList"] += checkboxes[i].value;
						else requestData["seqList"] += ("|" + checkboxes[i].value); 
					}
				}
				
				if(requestData["seqList"].length <= 0) {
					alert("삭제할 기록을 선택해 주세요.");
					return;
				} else {
					$.ajax({
						type: "post",
						url: "/wr/deleteWorkoutRecord",
						data: requestData,
						success: function(result) {
							alert("운동을 성공적으로 삭제했습니다!");
							document.getElementById("btnModalClose").click();
						},
						error : function(xhr, textStatus, errorThrown) {
							alert("운동을 삭제하는 데 실패하였습니다..\n" + xhr.status + " " + xhr.statusText);
						}
					});
				}
			}
			
			/*
			* INSERT WORKOUT RECORD INTO DB
			*/
			function saveWorkout() {
				/*
				* 운동일자
				* 메인부위코드
				* 종목코드
				* 무게
				* 쉬는시간
				* 반복횟수
				* 각 세트당 반복횟수(string, | 구분자)
				* 
				*/
				var woDt = document.getElementById("selectedModalYmd").innerHTML.replace('/','').replace('/','');
				var inpPartMain = document.getElementById("inpPartMain").value;
				var inpWo = document.getElementById("inpWo").value;
				var inpWeight = document.getElementById("inpWeight").value;
				var inpRestTime = document.getElementById("inpRestTime").value;
				var inpReps = document.getElementsByClassName("inpReps");
				var strReps = "";
				var inpSets = inpReps.length;
				
				/* INPUT VALUE CHECK */
				
				if(!inpPartMain) {
					alert("선택된 운동 부위가 없습니다.");
					return;
				}
				if(!inpWo) {
					alert("선택된 운동 종목이 없습니다.");
					return;
				}
				if(!inpWeight) {
					alert("중량을 입력해 주세요.");
					return;
				} else if(inpWeight < 1 ) {
					alert("중량은 자연수만 입력 가능합니다.");
					return;
				}
				if(!inpRestTime) {
					alert("쉬는 시간을 입력해 주세요.");
					return;
				} else if(inpRestTime < 1) {
					alert("쉬는 시간은 자연수만 입력 가능합니다.");
					return;
				}
				if(inpSets < 1) {
					alert("각 세트 반복 횟수를 입력해 주세요.");
					createRepsInput();
					return;
				}
				for(var i = 0; i < inpSets; ++i) {
					if(!inpReps[i].value) {
						alert((i+1) + "번째 세트 반복 횟수를 입력해 주세요.");
						return;
					} else if (inpReps[i].value < 1) {
						alert((i+1) + "번째 세트 반복 횟수를 자연수로 입력해 주세요.");
						return;
					}
					if(i != 0) strReps += "|";
					strReps += inpReps[i].value;					
				}
				
				var requestData = {
						usrId: 'sehoakasayho',/*FOR TEST*/
						woDt: woDt,
						partMainCod: inpPartMain,
						woCod: inpWo,
						restTimeSec: inpRestTime,
						sets : inpSets,
						reps : strReps,
						weight: inpWeight
				};
				console.log("insert workout!");
				console.log(requestData);
				
				$.ajax({
					type: "post",
					url: "/wr/insertWorkoutRecord",
					data: requestData,
					success: function(result) {
						alert("운동을 성공적으로 등록했습니다!");
						document.getElementById("btnModalClose").click();
						/* REFRESH PAGE */
						buildCalendar(Number(woDt.substr(0,4)), Number(woDt.substr(4,2))-1);
						chartInit();
					},
					error : function(xhr, textStatus, errorThrown) {
						alert("운동을 등록하는 데 실패하였습니다..\n" + xhr.status + " " + xhr.statusText);
					}
				});
			}