function showTime(){
  var now = new Date();
  var h = now.getHours();
  var m = now.getMinutes();
  var s = now.getSeconds();
  m = checkTime(m);
  s = checkTime(s);
  document.querySelector('.current-time').innerHTML =
  h + ":" + m + ":" + s;
}
function checkTime(i) {
  if (i < 10) {i = "0" + i};
  return i;
}

setInterval(showTime,1000);