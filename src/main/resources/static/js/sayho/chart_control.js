/* FUNCTIONS FOR CHART CONTROL */			
/* {startDate : '20200707'(char(8)), months : 3(number)}*/
			function chartInit() {
				var td = new Date();
				$.ajax({
					type: "post",
					url: "/chart/getMonthlyChartData",
					data: {
						startDate: getYymmddFromDate(td),/*FOR TEST*/
						months: 3,
						usrId: 'sehoakasayho'
					},
					success: function(result) {
						console.log('chart data init success!');
						console.log(result.chartDataChest);
						console.log(result.chartDataBack);
						console.log(result.chartDataLeg);
						for(var i = 0; i < result.chartDataChest.length; ++i) {
							global_chart_data_chest.push([result.chartDataChest[i]["wo_dt"],
								result.chartDataChest[i]["volume"]]);
						}
						for(var i = 0; i < result.chartDataBack.length; ++i) {
							global_chart_data_back.push([result.chartDataBack[i]["wo_dt"],
								result.chartDataBack[i]["volume"]]);
						}
						for(var i = 0; i < result.chartDataLeg.length; ++i) {
							global_chart_data_leg.push([result.chartDataLeg[i]["wo_dt"],
								result.chartDataLeg[i]["volume"]]);
						}
						/* DRAW CHART */
						drawCharts();
					},
					error : function(xhr, textStatus, errorThrown) {
						alert("차트 데이터 초기화에 실패했습니다..\n" + xhr.status + " " + xhr.statusText);
					}
				});
			}
			function drawCharts(){
				drawChartChest();
				drawChartBack();
				drawChartLeg();
			}
			function drawChartChest() {
				var data = new google.visualization.DataTable();
				data.addColumn('string', '일자');
				data.addColumn('number', 'volume');
				data.addRows(global_chart_data_chest);
				var options = {
					title: '볼륨-가슴',
				    subtitle: '(단위:kg)',
				    legend: { position: 'bottom' }/*,
				    width: $("#workoutChartChest").width(),
				    height: 500*/
				};
				var chart = new google.visualization.LineChart(document.getElementById('workoutChartChest'));
				chart.draw(data, options);
			}
			function drawChartBack() {
				var data = new google.visualization.DataTable();
				data.addColumn('string', '일자');
				data.addColumn('number', 'volume');
				data.addRows(global_chart_data_back);
				var options = {
					title: '볼륨-등',
				    subtitle: '(단위:kg)',
				    legend: { position: 'bottom' }
				
					/*,
				    width: $("#workoutChartChest").width(),
			        height: 500*/
				};
				var chart = new google.visualization.LineChart(document.getElementById('workoutChartBack'));
				chart.draw(data, options);
			}
			function drawChartLeg() {
				var data = new google.visualization.DataTable();
				data.addColumn('string', '일자');
				data.addColumn('number', 'volume');
				data.addRows(global_chart_data_leg);
				var options = {
					title: '볼륨-하체',
				    subtitle: '(단위:kg)',
				    legend: { position: 'bottom' }/*,
				    width: $("#workoutChartChest").width(),
				    height: 500*/
				};
				var chart = new google.visualization.LineChart(document.getElementById('workoutChartLeg'));
				chart.draw(data, options);
			}
			/*
			function drawChart() {
				var data = new google.visualization.DataTable();
		        data.addColumn('string', '일자');
		        data.addColumn('number', '가슴');
		        data.addColumn('number', '등');
		        data.addColumn('number', '하체');
		        data.addRows(global_chart_data);

		        var options = {
		          title: '부위별 볼륨',
		          subtitle: '(단위:kg)',
		          legend: { position: 'bottom' }
		        };

		        var chart = new google.visualization.LineChart(document.getElementById('workoutChartChest'));

		        chart.draw(data, options);
			}
			*/