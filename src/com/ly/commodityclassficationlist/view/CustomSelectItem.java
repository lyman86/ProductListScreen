package com.ly.commodityclassficationlist.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.productlistscreen.R;
import com.ly.commodityclassficationlist.utils.DensityUtils;
public class CustomSelectItem extends View {
	private Context context;
	/**
	 * 定义左边，右边，和右边旁边的类型
	 */
	private int leftType;
	private int rightType;
	private int rightSideType;
	/**
	 * 左边文字的属性
	 */
	private String leftText = "";
	private int leftTextColor;
	private float leftTextSize;
	private float leftTextPaddingLeft;
	/**
	 * 右边文字的属性
	 */
	private String rightText = "";
	private int rightTextColor;
	private float rightTextSize;
	private float rightTextPaddingRight;
	/**
	 * 右边旁边文字的属性
	 */
	private String rightSideText = "";
	private int rightSideTextColor;
	private float rightSideTextSize;
	private float rightSideTextPaddingRight;
	/**
	 * 右边图片的属性
	 */
	private int rightImageScource;
	private float rightImagePaddingRight; 
	private int rightImageWidth;
	/**
	 * 整个View的宽高
	 */
	private int viewWidth;
	private int viewHeight;
	/**
	 * 定义画笔
	 */
	private Paint paint;
	/**
	 * 定义设置文字的矩形
	 */
	private Rect bounds;
	private final int LEFT_TYPE_TEXT = 0;
	private final int LEFT_TYPE_IMAGE = 1;
	private final int RIGHT_TYPE_TEXT = 0;
	private final int RIGHT_TYPE_IMAGE = 1;
	private final int RIGHT_SIDE_TYPE_TEXT = 0;
	private final int RIGHT_SIDE_TYPE_IMAGE = 1;

	public CustomSelectItem(Context context) {
		this(context, null);
	}
	public CustomSelectItem(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public CustomSelectItem(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.itemView, defStyleAttr, 0);
		//初始化类型
		initType(ta);
		ta.recycle();
	}
	/**
	 * 初始化类型
	 */
	private void initType(TypedArray ta) {
		leftType = ta.getInt(R.styleable.itemView_leftType, -1);
		rightType = ta.getInt(R.styleable.itemView_rightType, -1);
		rightSideType = ta.getInt(R.styleable.itemView_rightSideType, -1);
		initAttribute(ta);
	}
	/**
	 * 初始化各个属性
	 * @param ta
	 */
	private void initAttribute(TypedArray ta) {
		switch (leftType) {
		case LEFT_TYPE_TEXT:
			getLeftTextFromXml(ta);
			break;
		case LEFT_TYPE_IMAGE:
			//TODO
			break;
		}
		switch (rightType) {
		case RIGHT_TYPE_TEXT:
			getRightTextFromXml(ta);
			break;
		case RIGHT_TYPE_IMAGE:
			basePaintInit();
			getRightImageFromXml(ta);
			break;
		}
		switch (rightSideType) {
		case RIGHT_SIDE_TYPE_TEXT:
			getRightSideTextFromXml(ta);
			break;
		case RIGHT_SIDE_TYPE_IMAGE:
			//TODO
			break;
		}
	}
	private void basePaintInit(){
		paint =new Paint();
		paint.setAntiAlias(true);
	}
	/**
	 * 定义文字时候所初始化的画笔
	 * @param text
	 * @param textColor
	 * @param textSize
	 */
	private void initPaint(String text,int textColor,float textSize) {
		basePaintInit();
		bounds = new Rect();
	    paint.setColor(textColor);
	    paint.setTextSize(textSize);
	    paint.getTextBounds(text, 0, text.length(), bounds);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		viewWidth = getMeasuredWidth();
		viewHeight = getMeasuredHeight();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		switch (leftType) {
		case LEFT_TYPE_TEXT:
			initPaint(leftText,leftTextColor,leftTextSize);
			drawLeftText(canvas);
			break;
		case LEFT_TYPE_IMAGE:
			//TODO
			break;
		}
		switch (rightType) {
		case RIGHT_TYPE_TEXT:
			initPaint(rightText,rightTextColor,rightTextSize);
			drawRightText(canvas);
			break;
		case RIGHT_TYPE_IMAGE:
			drawRightImage(canvas);
			break;
		}
		switch (rightSideType) {
		case RIGHT_SIDE_TYPE_TEXT:
			initPaint(rightSideText,rightSideTextColor,rightSideTextSize);
			drawRightSideText(canvas);
			break;
		case RIGHT_SIDE_TYPE_IMAGE:
			//TODO
			break;
		}
	}
	/**
	 * 画右边的图片
	 * @param canvas
	 */
	private void drawRightImage(Canvas canvas) {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), rightImageScource);
		int imageWidth = bitmap.getWidth();
		int imageHeight = bitmap.getHeight();
		float scaleWidth = viewWidth/imageWidth;
		float scaleHeight =viewHeight/imageHeight;
		float scale = Math.min(scaleWidth, scaleHeight);
		Matrix matrix = new Matrix();
		matrix.postScale(scale/2, scale/2);
		Bitmap res = Bitmap.createBitmap(bitmap, 0,0,imageWidth, imageHeight, matrix, true);
		canvas.drawBitmap(res, viewWidth-res.getWidth()-rightImagePaddingRight, viewHeight/2-res.getHeight()/2, paint);
		rightImageWidth = res.getWidth();
	}
	/**
	 * 画右边的文字
	 * @param canvas
	 */
	private void drawRightText(Canvas canvas) {
		canvas.drawText(rightText, viewWidth-bounds.width()-rightTextPaddingRight, bounds.height()+(viewHeight-bounds.height())/2, paint);
	}
	/**
	 * 画右边旁边的文字
	 * @param canvas
	 */
	private void drawRightSideText(Canvas canvas) {
		canvas.drawText(rightSideText, viewWidth-bounds.width()-rightSideTextPaddingRight-rightImageWidth-rightImagePaddingRight, bounds.height()+(viewHeight-bounds.height())/2-bounds.bottom, paint);
	}
	/**
	 * 画左边的文字
	 * @param canvas
	 */
	private void drawLeftText(Canvas canvas) {
		canvas.drawText(leftText, leftTextPaddingLeft, bounds.height()+(viewHeight-bounds.height())/2-bounds.bottom, paint);
	}
	/**
	 * 从xml文件中获取左边文字的属性
	 * @param ta
	 */
	private void getLeftTextFromXml(TypedArray ta) {
		leftText = ta.getString(R.styleable.itemView_leftText);
		leftTextColor = ta.getColor(R.styleable.itemView_leftTextColor,Color.BLACK);
		leftTextSize = ta.getDimension(R.styleable.itemView_leftTextSize, 14.0f);
		if (leftTextSize != 14.0f) {
			leftTextSize = DensityUtils.px2sp(context, leftTextSize);
		}
		// 默认为0
		leftTextPaddingLeft = DensityUtils.px2dp(context,ta.getDimension(R.styleable.itemView_leftTextPaddingLeft, 0));
	}
	/**
	 * 从xml文件中获取右边文字的属性
	 * @param ta
	 */
	private void getRightTextFromXml(TypedArray ta) {
		rightText = ta.getString(R.styleable.itemView_rightText);
		rightTextColor = ta.getColor(R.styleable.itemView_rightTextColor,Color.BLACK);
		rightTextSize = ta.getDimension(R.styleable.itemView_rightTextSize, 14.0f);
		if (rightTextSize != 14.0f) {
			rightTextSize = DensityUtils.px2sp(context, rightTextSize);
		}
		// 默认为0
		rightTextPaddingRight = DensityUtils.px2dp(context,
				ta.getDimension(R.styleable.itemView_rightTextPaddingRight, 0));
	}
	/**
	 * 从xml文件中获取右边旁边文字的属性
	 * @param ta
	 */
	private void getRightSideTextFromXml(TypedArray ta) {
		rightSideText = ta.getString(R.styleable.itemView_rightSideText);
		rightSideTextColor = ta.getColor(R.styleable.itemView_rightSideTextColor,Color.BLACK);
		rightSideTextSize = ta.getDimension(R.styleable.itemView_rightSideTextSize, 14.0f);
		if (rightSideTextSize != 14.0f) {
			rightSideTextSize = DensityUtils.px2sp(context, rightSideTextSize);
		}
		// 默认为0
		rightSideTextPaddingRight = DensityUtils.px2dp(context,
				ta.getDimension(R.styleable.itemView_rightSideTextPaddingRight, 0));
		
	}
	/**
	 * 从xml文件中获取右边图片的属性
	 * @param ta
	 */
	private void getRightImageFromXml(TypedArray ta) {
		rightImageScource = ta.getResourceId(R.styleable.itemView_rightImageSource, R.drawable.ic_launcher);
		rightImagePaddingRight = DensityUtils.px2dp(context,
				ta.getDimension(R.styleable.itemView_rightImagePaddingRight, 0));
	}
	
	public void setRightText(String rightText){
		this.rightSideText = rightText;
		postInvalidate();
	}
	
}
