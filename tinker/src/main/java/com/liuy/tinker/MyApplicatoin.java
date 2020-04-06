package com.liuy.tinker;

import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * description:
 * author: freed on 2020/4/5
 * email: 674919909@qq.com
 * version: 1.0
 */
public class MyApplicatoin extends TinkerApplication {

   public MyApplicatoin(){
       super(ShareConstants.TINKER_ENABLE_ALL,TinkerApplicationLike.class.getName(), TinkerLoader.class.getName(),false);
   }

}
