package cn.henryzhuhao.easynews.business.Trasitions;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

/**
 * Created by HenryZhuhao on 2017/5/14.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class DetailTransitions extends TransitionSet{
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DetailTransitions() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform()).
                addTransition(new ChangeImageTransform());
    }
}
