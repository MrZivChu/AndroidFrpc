package com.ironxiao.frpc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends Activity {
    private static final String DEFAULT_FRPC_CONFIG_FILE = "frpc.ini";
    private File FRPC_CONFIG_FILE;

    private EditText etServerAddr, etServerPort, etServerToken, etLocalConfigName, etLocalType, etLocalIp, etLocalPort, etRemotePort;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FRPC_CONFIG_FILE = new File(getFilesDir(), DEFAULT_FRPC_CONFIG_FILE);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadConfig();
    }

    private void initViews() {
        etServerAddr = findViewById(R.id.et_s_addr);
        etServerPort = findViewById(R.id.et_s_port);
        etServerToken = findViewById(R.id.et_s_token);
        etLocalConfigName = findViewById(R.id.et_l_name);
        etLocalType = findViewById(R.id.et_l_type);
        etLocalIp = findViewById(R.id.et_l_ip);
        etLocalPort = findViewById(R.id.et_l_port);
        etRemotePort = findViewById(R.id.et_r_port);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.frpc_start:
                saveConfig();
                startFrpc();
                break;
            case R.id.frpc_reset:
                loadDefaultConfigFromAssets();
                loadConfig();
                break;
            case R.id.frpc_quit:
                quitFrpc();
                break;
            default:
                break;
        }
    }


    private void startFrpc() {
        Intent intent = new Intent(this, FrpcService.class);
        startService(intent);
    }

    private void quitFrpc() {
        Intent intent = new Intent(this, FrpcService.class);
        stopService(intent);
//        System.exit(0);
    }

    private void loadConfig() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(FRPC_CONFIG_FILE));
            String str;
            while ((str = in.readLine()) != null) {
                handleViewsByMsg(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleViewsByMsg(String str) {
        String[] infos = str.split("\\R");
        for (String info : infos) {
            String[] kv = info.trim().split("=");
            if (kv.length == 2) {
                EditText toSetEt = null;
                switch (kv[0].trim()) {
                    case FrpcConfig.KEY_SERVER_ADDR:
                        toSetEt = etServerAddr;
                        break;
                    case FrpcConfig.KEY_SERVER_PORT:
                        toSetEt = etServerPort;
                        break;
                    case FrpcConfig.KEY_SERVER_TOKEN:
                        toSetEt = etServerToken;
                        break;
                    case FrpcConfig.KEY_LOCAL_TYPE:
                        toSetEt = etLocalType;
                        break;
                    case FrpcConfig.KEY_LOCAL_IP:
                        toSetEt = etLocalIp;
                        break;
                    case FrpcConfig.KEY_LOCAL_PORT:
                        toSetEt = etLocalPort;
                        break;
                    case FrpcConfig.KEY_REMOTE_PORT:
                        toSetEt = etRemotePort;
                        break;
                    default:
                        break;

                }
                if (toSetEt != null) {
                    toSetEt.setText(kv[1].trim());
                }
            } else if (info.startsWith("[")) {
                if (!"[common]".equals(info)) {
                    etLocalConfigName.setText(info.substring(1, info.length() - 1));
                }
            }
        }
    }

    private synchronized void loadDefaultConfigFromAssets() {
        try {
            InputStream in = getAssets().open(DEFAULT_FRPC_CONFIG_FILE);
            OutputStream out = new FileOutputStream(FRPC_CONFIG_FILE);
            byte[] buff = new byte[1024];
            int len;
            while ((len = in.read(buff)) > 0)
                out.write(buff, 0, len);
            in.close();
            out.close();
        } catch (IOException e) {
            Toast.makeText(this, getString(R.string.ts_load_default_config_fail), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, getString(R.string.ts_load_default_config_success), Toast.LENGTH_SHORT).show();

    }

    public void saveConfig() {
        FrpcConfig config = new FrpcConfig();
        FrpcConfig.Node nodeCommon = new FrpcConfig.Node("common");
        nodeCommon.add(FrpcConfig.KEY_SERVER_ADDR, etServerAddr.getText().toString());
        nodeCommon.add(FrpcConfig.KEY_SERVER_PORT, etServerPort.getText().toString());
        nodeCommon.add(FrpcConfig.KEY_SERVER_TOKEN, etServerToken.getText().toString());
        config.addNode(nodeCommon);

        FrpcConfig.Node nodeLocal = new FrpcConfig.Node(etLocalConfigName.getText().toString());
        nodeLocal.add(FrpcConfig.KEY_LOCAL_TYPE, etLocalType.getText().toString());
        nodeLocal.add(FrpcConfig.KEY_LOCAL_IP, etLocalIp.getText().toString());
        nodeLocal.add(FrpcConfig.KEY_LOCAL_PORT, etLocalPort.getText().toString());
        nodeLocal.add(FrpcConfig.KEY_REMOTE_PORT, etRemotePort.getText().toString());
        config.addNode(nodeLocal);

        config.saveTo(FRPC_CONFIG_FILE.getAbsolutePath());
    }
}