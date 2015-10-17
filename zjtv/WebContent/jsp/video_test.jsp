<%--
  Created by IntelliJ IDEA.
  User: yunchunnan
  Date: 14-4-15
  Time: 下午2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script language="javascript" src="http://qzs.qq.com/tencentvideo_v1/js/tvp/tvp.player.js" charset="utf-8"></script>
<div id="mod_player"><!-- 这个div是播放器准备输出的位置 --></div>
<script language="javascript">
    var video = new tvp.VideoInfo();
    //向视频对象传入视频vid
    //%3Db0014zc2vpb
    video.setVid("e0116r5u35p");

    var player = new tvp.Player(300, 250);
    player.addParam("player","html5");
    player.addParam("adplay",0);
    //设置播放器初始化时加载的视频
    player.setCurVideo(video);
    //输出播放器
    player.write("mod_player");
</script>