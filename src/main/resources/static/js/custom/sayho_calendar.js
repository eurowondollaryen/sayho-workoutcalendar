/**
 * calendar 관련 functions
 */

/* BUILD CALENDAR AND GET WORKOUT RECORD OF THE MONTH */
			function buildCalendar(year, month) {
				
				/* THEN CLEAR CALENDAR */
				var calendar = document.getElementById("calendar");
				calendar.innerHTML = ""
				
				/* SET TODAY TO FIRST DAY OF THE MONTH */
				var today = new Date();
				today.setYear(year);
				today.setMonth(month);
				today.setDate(1);
				year = today.getFullYear();
				month = today.getMonth();
				
				/* SET CALENDAR TITLE */
				var calendar_title_wrapper = document.createElement("DIV");
				calendar_title_wrapper.classList.add("sayho-row");
				var calendar_title_left = document.createElement("DIV");
				calendar_title_left.classList.add("sayho-calendar-left");
				calendar_title_left.classList.add("sayho-col-2");
				calendar_title_left.setAttribute("onClick", "buildCalendar(" + year + ", " + (month-1) + ")");
				calendar_title_left.innerHTML = "◀";
				var calendar_title_right = document.createElement("DIV");
				calendar_title_right.classList.add("sayho-calendar-right");
				calendar_title_right.classList.add("sayho-col-2");
				calendar_title_right.setAttribute("onClick", "buildCalendar(" + year + ", " + (month+1) + ")");
				calendar_title_right.innerHTML = "▶";
				var calendar_title = document.createElement("DIV");
				calendar_title.classList.add("sayho-calendar-title");
				calendar_title.classList.add("sayho-col-6");
				calendar_title.innerHTML = year + "년 " + (month+1) + "월";
				
				calendar_title_wrapper.appendChild(calendar_title_left);
				calendar_title_wrapper.appendChild(calendar_title);
				calendar_title_wrapper.appendChild(calendar_title_right);
				
				calendar.appendChild(calendar_title_wrapper);
				
				
				var dayofweek = today.getDay();
				if(dayofweek != 0) {
					today.setDate(1-dayofweek);
				}
				/* LETS BUILD CALENDAR OF THE MONTH */
				for(var i = 0; i < 6; ++i) {//7x6=42
					var row = document.createElement("DIV");
					row.classList.add("sayho-row");
					for(var j = 0; j < 7; ++j) {
						var cell = document.createElement("DIV");
						
						cell.setAttribute("id", "cell" + getYymmddFromDate(today));
						cell.classList.add("sayho-col");
						cell.setAttribute("onClick", "addWorkout(" + today.getFullYear() + "," + (today.getMonth()+1) + "," + today.getDate() + ")");
						cell.setAttribute("data-toggle", "modal");
						cell.setAttribute("data-target", "#addWorkoutModal")
						
						if(today.getMonth() != month) {
							cell.classList.add("sayho-grayday");
						}
						else {
							if(today.getDay() == 0) {
								cell.classList.add("sayho-sunday");
							} else if(today.getDay() == 6) {
								cell.classList.add("sayho-saturday");
							}
						}
						
						cell.innerHTML = "<div class='sayho-date-number'>" + today.getDate() + "</div>";
						
						row.appendChild(cell);
						today.setDate(today.getDate()+1);
					}
					calendar.appendChild(row);
				}
				
				/* GET WORKOUT RECORD */
				var requestData = {
						inpUsrId: 'sehoakasayho',/* FOR TEMP */
						inpWoDt: year + ((month+1) < 10 ? "0" + (month+1) : "" + (month+1))
				};
				
				$.ajax({
					type: "post",
					url: "/wr/selectWorkoutRecord",
					data: requestData,
					success: function(result) {
						console.log('requesting workout record success!');
						console.log(result);
						global_workout_data = result.data;
						for(var i = 0; i < global_workout_data.length; ++i) {
							var targetCell = document.getElementById("cell" + global_workout_data[i]["wo_dt"]);
							var wrElem = document.createElement("DIV");
							wrElem.setAttribute("id", "wr" + i);
							wrElem.classList.add("workout-record");
							wrElem.setAttribute("onClick", "showWorkoutRecord(" + i + ")");
							wrElem.innerHTML = global_workout_data[i]["part_main_nm"] + "-" + global_workout_data[i]["wo_nm_kor"];
							targetCell.appendChild(wrElem);
						}
					},
					error : function(xhr, textStatus, errorThrown) {
						alert("운동 기록을 가져오는 데 실패했습니다..\n" + xhr.status + " " + xhr.statusText);
					}
				});
			}
			