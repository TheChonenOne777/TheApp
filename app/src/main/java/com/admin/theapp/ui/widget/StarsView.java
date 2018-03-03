package com.admin.theapp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.admin.theapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @deprecated Use {@link android.widget.RatingBar}
 * */
@Deprecated
public class StarsView extends LinearLayout {

    @BindView(R.id.stars_text)
    TextView starsText;

    private static final int MAX_STARS_COUNT = 5;

    private double starsCount;
    private Drawable fullStarIcon;
    private Drawable halfStarIcon;

    public StarsView(@NonNull Context context) {
        this(context, null, 0);
    }

    public StarsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.stars_view, this);
        ButterKnife.bind(this);
        fullStarIcon = ContextCompat.getDrawable(context, R.drawable.star_full);
        halfStarIcon = ContextCompat.getDrawable(context, R.drawable.star_half);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StarsView, 0, 0);
        try {
            starsCount = attributes.getFloat(R.styleable.StarsView_stars, 0f);
        } finally {
            attributes.recycle();
        }
        setStars(starsCount);
    }

    public void setStars(double stars) {
        this.starsCount = stars;
        int halfStarPosition = (int) Math.ceil(starsCount);
        halfStarPosition = halfStarPosition > MAX_STARS_COUNT ? MAX_STARS_COUNT : halfStarPosition;
        ImageView halfStarView = findViewWithTag(String.valueOf(halfStarPosition));
        if (halfStarView != null) {
            for (int i = 1; i <= halfStarPosition; i++) {
                ImageView fillStar = findViewWithTag(String.valueOf(i));
                fillStar.setImageDrawable(fullStarIcon);
            }
            if (halfStarPosition > starsCount) {
                halfStarView.setImageDrawable(halfStarIcon);
            }
            starsText.setText(String.valueOf(starsCount));
        } else {
            starsText.setText(R.string.star_view_not_rated_text);
        }
        invalidate();
    }
}
