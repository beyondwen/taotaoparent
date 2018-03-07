package com.taotao.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.taotao.common.utils.IDUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FtpTest {

    @Test
    public void ftpTest() throws IOException{
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.137.128");
        ftpClient.login("ftpuser","ftpuser");
        InputStream inputStream = new FileInputStream(new File("E:\\linux_system\\2.png"));
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.storeFile(IDUtils.genItemId()+".jpg",inputStream);
        inputStream.close();
        ftpClient.logout();
    }
}
