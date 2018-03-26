package app.cddic.com.smarter.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.utils
 * 文件名：  UtilTools
 * 创建者：  zhl
 * 创建时间： 2017/3/5 11:26
 * 描述：工具类
 */

public class UtilTools {

    //设置字体
    public static void setFont(Context mContext, TextView mTextView){
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(),"fonts/FONT.TTF");
        mTextView.setTypeface(fontType);
    }

}
