package com.magic.commons.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.magic.app.zjtv.common.Messages;
import com.magic.app.zjtv.dao.VersionDAO;
import com.magic.app.zjtv.model.Version;
import com.magic.commons.helper.HttpResultHelper;
import com.magic.commons.models.HttpDataResult;
import com.magic.commons.service.CommonService;
import com.magic.commons.utils.FileUtils;
import com.magic.commons.utils.RequestUtils;
import com.magic.core.annotation.NoAuth;
import com.magic.core.annotation.layout.LayoutNone;
import com.magic.qiniu.QiniuHelper;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;

/**
 * Created by Yin Jian Feng on 14-3-6.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	VersionDAO versionDAO;
    @Autowired
    CommonService service;
    
    
    @RequestMapping("/images/**")
    @ResponseBody
    public byte[] images(HttpServletRequest request) throws IOException {
        String filepath = request.getRequestURI().substring(request.getContextPath().length());
        filepath = filepath.substring("/common/images/".length(), filepath.length());
        String fileRoot = FileUtils.getRootFile();
        File file = FileUtils.getFile(fileRoot+ File.separator+filepath);
        return FileUtils.readFileToByteArray(file);
    }

    @RequestMapping("/qiniu/uptoken")
    @ResponseBody
    @NoAuth
    public HttpDataResult qiniu_uptoken(HttpDataResult result){
        Map<String, Object> data = new HashMap<String, Object>();
        String image_uptoken = "";
        String video_uptoken = "";
        String audio_uptoken = "";
        try {
            image_uptoken = QiniuHelper.generateUpToken(QiniuHelper.QINIU_BUCKET_IMAGE);
            video_uptoken = QiniuHelper.generateUpToken(QiniuHelper.QINIU_BUCKET_VIDEO);
            audio_uptoken = QiniuHelper.generateUpToken(QiniuHelper.QINIU_BUCKET_AUDIO);
        } catch (AuthException e) {
            e.printStackTrace();
        }
        data.put("image_uptoken", image_uptoken);
        data.put("video_uptoken", video_uptoken);
        data.put("audio_uptoken", audio_uptoken);
        result.setData(data);
        return result;
    }

    @RequestMapping("/qiniu/uploadimage")
    @ResponseBody
    public Map<String, Object> uploadShopItemImage(HttpServletRequest request){
        MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files =  fileRequest.getFiles("file");
        String fileKey = "";
        try {
            if (files != null && !files.isEmpty()) {
                String uptoken = QiniuHelper.generateUpToken(QiniuHelper.QINIU_BUCKET_IMAGE);
                MultipartFile file = files.get(0);
                PutExtra extra = new PutExtra();
                extra.mimeType = file.getContentType();
                PutRet putRet = IoApi.Put(uptoken, IoApi.UNDEFINED_KEY, file.getInputStream(), extra);
                fileKey = putRet.getKey();

            }
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("key", fileKey);
        resultMap.put("url", QiniuHelper.handleImageStr(fileKey));
        return resultMap;
    }


    @RequestMapping("/showqrcode/{msg}")
    @LayoutNone
    @NoAuth
    public String showqrcode(HttpServletRequest request, @PathVariable String msg){
    	request.setAttribute("msg", msg);
    	return "qrcode";
    }
    
    @RequestMapping("/qrcode/{msg}")
    @ResponseBody
    @NoAuth
    public void qrcode(@PathVariable String msg, 
    		HttpServletRequest request, HttpServletResponse response){
        int WIDTH = 400, HEIGHT = 400;
        
        try {
            OutputStream outputStream = response.getOutputStream();
            MultiFormatWriter formatWriter = new MultiFormatWriter();
            Map<EncodeHintType, Object> hinits = new HashMap<EncodeHintType, Object>();
            hinits.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            
            String basePath = RequestUtils.getBasePath(request);
            msg = basePath + "act/getactaward?uuid="+msg;
            
            BitMatrix bitMatrix = formatWriter.encode(msg, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);

//            MatrixToImageWriter.writeToPath(bitMatrix, "png", imageFile.toPath());
            MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/checkVersion")
    @ResponseBody
    @NoAuth
    public HttpDataResult checkVersion(HttpServletRequest request, String platform,  HttpDataResult result){
    	if(platform == null){
//			这里的ID是必选项目，枚举类Messages下面包含了预定义好的各种错误信息，具体信息定义在messages.properties文件中
			return (HttpDataResult) HttpResultHelper.handleJsonErrorResult(result, Messages.EC_PARAMETER_ERR);
		}
        platform = platform.toLowerCase();
        Version version = service.getVersion(platform);
        Map<String, Object> map = new HashMap<String, Object>();
        if (version != null) {
			map.put("versionChange", version.getChanges());
			map.put("platform", version.getPlatform());
			map.put("downLoadUrl", version.getDownloadUrl());
			map.put("versionCode", version.getVersionCode());
			map.put("fileName", version.getFilename());
		}
		result.setData(map);
    	return result;
    }

    
}
