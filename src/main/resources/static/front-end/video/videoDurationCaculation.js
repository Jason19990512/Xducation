// 自動填寫影片時間
// 於 edit course page 自動寫入上傳的檔案時間的
$("input[name=video]").change(setVideoDuration);

function setVideoDuration() {
	window.URL = window.URL || window.webkitURL;
	let changedFileInput = $(this)[0];
	if (this.files != null) {
		file = this.files[0];
		// console.log("file type = " + file.type);
		if (file.type.indexOf("video/mp4") > -1) {
			let video = document.createElement('video');
			video.preload = 'metadata';
			video.src = URL.createObjectURL(file);;
			video.onloadedmetadata = function () {
				window.URL.revokeObjectURL(video.src);
				let duration = parseInt(video.duration);
				$(changedFileInput).prevAll("input[name=chapterlen]").val(duration);
				$(changedFileInput).prevAll(".showVideoLen").text(convertSecToHrFormatter(duration));
				//video = null;
				// console.log(file.name.substring(0,file.name.indexOf(".mp4")))
				$(changedFileInput).parents("tr").children("td").children("input[name='chaptername']").val(file.name.substring(0,file.name.indexOf(".mp4")));
			}
		} else {
			swal('提醒', '課程影片僅支援 MP4 格式之影片檔案',  'warning');
			$(this).val(null);
			$(this).prevAll("input[name=chapterlen]").val(0);
		}
	}
}

function showInitOriginalVideoLen() {
	for (let i = 0; i < $(".showVideoLen").length; i++) {
		$(".showVideoLen")[i].innerText = convertSecToHrFormatter($(".showVideoLen").nextAll("input[name=chapterlen]")[i].value);
	}
}

function convertSecToHrFormatter(seconds) {
	// Hours, minutes and seconds
	let hrs = ~~(seconds / 3600);
	let mins = ~~((seconds % 3600) / 60);
	let secs = ~~seconds % 60;

	// Output like "1:01" or "4:03:59" or "123:03:59"
	var ret = "";

	if (hrs > 0) {
		ret += "" + hrs + " : " + (mins < 10 ? "0" : "");
	}

	ret += "" + mins + " : " + (secs < 10 ? "0" : "");
	ret += "" + secs;
	return ret;
}