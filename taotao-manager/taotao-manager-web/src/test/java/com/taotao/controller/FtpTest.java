package com.taotao.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpTest {

    @Test
    public void ftpTest() throws IOException{
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.163.128");
        ftpClient.login("ftpuser","ftpuser");
        InputStream inputStream = new FileInputStream(new File("D:\\photo\\P003.jpg"));
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.changeWorkingDirectory("/home/ftpuser/images");
        ftpClient.storeFile("1235.jpg",inputStream);
        inputStream.close();
        ftpClient.logout();
    }
}
