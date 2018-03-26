package app.cddic.com.smarter.service.protocol;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.service.CommInfo;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;

/**
 * Created by yfs on 4/15 0015.
 */

public class Protocol {
    SmartService mSrv;
    CommInfo commInfo;

    SmartProtocol[] mProtocol = new SmartProtocol[12];
    public Map<Integer,Integer> cmdIndex;

    public Protocol(SmartService Srv) {
        this.mSrv = Srv;
        commInfo = mSrv.getCommInfo();

        mProtocol[0] = new TestProtocol(mSrv);
        mProtocol[1] = new LoginProtocol(mSrv);
        mProtocol[2] = new AccountProtocol(mSrv);
        mProtocol[3] = new ControlProtocol(mSrv);
        mProtocol[4] = new QueryProtocol(mSrv);
        mProtocol[5] = new MessageProtocol(mSrv);
        mProtocol[6] = new LanfindProtocol(mSrv);
        mProtocol[7] = new MyFileProtocol(mSrv);
        mProtocol[8] = new SecurityProcotol(mSrv);
        mProtocol[9] = new RelaterProtocol(mSrv);
        mProtocol[10] = new ContactProtocol(mSrv);
        mProtocol[11] = new UpdateProtocol(mSrv);

        cmdIndex = new HashMap<Integer,Integer>();
        cmdIndex.put(10,0); //连通测试
        cmdIndex.put(11,1); //登录
        cmdIndex.put(12,1); //登出
        cmdIndex.put(27,1); //保活

        cmdIndex.put(13,2); //注册
        cmdIndex.put(14,2); //修改
        cmdIndex.put(15,2); //注销
        cmdIndex.put(16,3); //控制

        cmdIndex.put(1,4); //查询
        cmdIndex.put(2,5); //请求
        cmdIndex.put(3,6); //局域网发现
        cmdIndex.put(4,7); //文件传输
        cmdIndex.put(26,8); //加密

        cmdIndex.put(17,9); //添加关联
        cmdIndex.put(18,9); //删除关联
        cmdIndex.put(19,9); //获取关联
        cmdIndex.put(20,9); //变更关联
        cmdIndex.put(5,9); //关联通告

        cmdIndex.put(21,10); //添加联系
        cmdIndex.put(22,10); //删除联系
        cmdIndex.put(23,10); //获取联系
        cmdIndex.put(24,10); //变更联系
        cmdIndex.put(6,10); //关联通告
        cmdIndex.put(7,11); //查询更新
    }

    //对外部接口
    public boolean sendProc(MsgObject msgObject){
        int cmd = cmdIndex.get(msgObject.getType());
        Log.i("test","cmd="+cmd);
        return mProtocol[cmd].protocolSend(msgObject);
    }

    public void recvProc(PacketMsg pkt){
        Log.d("asd","address"+pkt.address);
        Log.d("asd","cmd is"+pkt.cmd);
        PacketMsg ret = mProtocol[pkt.cmd].protocolRecv(pkt);
        if(ret != null){
            if (mProtocol[ret.cmd].sendProc(ret)){
                Log.i("log","ret.cmd="+ret.cmd+"\nret.type="+ret.type);
                if (pkt.type == 4 && pkt.cmd ==1){
                    return;
                }
                SmartProtocol.proSendProc(ret);
            }
        }


        }

    public void timeoutProc(PacketMsg pkt) {
        mProtocol[pkt.cmd].timeoutProc(pkt);
    }
}
