# CanvasLearn2
自定义控件之Canvas学习
>用了跨度为2.5个月的业余时间（中间有一个春节，都用来玩了（捂脸））看完了GcsSloop的[安卓自定义View教程目录](http://www.gcssloop.com/customview/CustomViewIndex/)，在做笔记的同时，对教程中一些demo做了代码的编写。这里做一个大概的声明。下面是我做的一个简要readme文档。
#### 一，先放一张Canvas的常用操作索引表截图（请原谅我为了偷懒放了一张截图...）
![Canvas常用操作索引表截图](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/canvas%E5%B8%B8%E7%94%A8%E6%93%8D%E4%BD%9C%E6%88%AA%E5%9B%BE.png)<br>
从截图中我们可以看到，Canvas的Api还是比较多的，从基础的绘制点、直线、矩形、圆等基本图形，到后面的配合上Path进行更复杂的操作，再到后面使用Matrix，可以说功能是非常的强大了。
#### 二，我的这个项目中做了一些api的联系，具体实现的效果有
##### (一)饼状图
![饼状图](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/%E9%A5%BC%E7%8A%B6%E5%9B%BE.png)<br>
实现原理：主要是使用了Canvas的drawArc方法，绘制圆弧<br>
代码片段：

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData) {
            return;
        }
        float currentStartAngle = mStartAngle; //当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2); //将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8); //饼状图半径
        rectF.set(-r, -r, r, r);
        Log.e("--------------------", "'----------------------onDraw");

        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rectF, currentStartAngle, pie.getAngle(), true, mPaint);
            currentStartAngle += pie.getAngle();
        }
    }
    
##### (二)画布缩放操作之for循环
![画布缩放操作之for循环](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/CanvasScaleForRecyclerDemo.png)<br>
实现原理：使用了一个for循环，每次进行缩小，然后绘制矩形（drawRect方法）<br>
代码片段：
  
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        rectF.set(-400, -400, 400, 400);

        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, mPaint);
        }
    }

##### (三)画布旋转操作之for循环
![画布旋转操作之for循环](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/%E7%94%BB%E5%B8%83%E6%97%8B%E8%BD%AC%E6%93%8D%E4%BD%9C.png)<br>
实现原理：使用了一个for循环，每次进行旋转，然后绘制直线（drawLine方法）<br>
代码片段：

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0, 0, 400, mPaint);
        canvas.drawCircle(0, 0, 380, mPaint);

        for (int i = 0; i < 360; i += 10) {
            canvas.drawLine(0, 380, 0, 400, mPaint);
            canvas.rotate(10);
        }
    }
##### (四)绘制图片之drawBitmap使用：CheckView，用一张素材图片实现一个点击gif效果<br>
![CheckView](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/CheckView.gif)<br>
实现原理：在被点击后，发送一个消息到handler中，在handler的handleMessage方法中检查动画状态，对于不同的动画状态做不同的操作<br>
代码片段：<br>
handleMessage方法中部分代码：<br>
     
     if (animState == ANIM_NULL)
                        return;
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animState--;
                    }
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    
onDraw方法中<br>
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 240, mPaint);

        //得出图像边长
        int sideLength = mBitmap.getHeight();

        //得到图像选区和实际绘制位置
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        Rect dst = new Rect(-200, -200, 200, 200);

        //绘制
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

##### (五)使用Path制作的一个雷达图<br>
![雷达图](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/Path%E4%BD%BF%E7%94%A8%E4%B9%8B%E9%9B%B7%E8%BE%BE%E5%9B%BE.png)<br>
实现原理：大量使用了Path类的一些操作。按照绘制正多边形，绘制直线，绘制文字，绘制区域的顺序完成了雷达图的绘制。这里面涉及到了正多边形的各个顶点左边位置的计算。<br>
代码片段：<br>

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);//绘制正多边形
        drawLines(canvas);//绘制直线
        drawText(canvas);//绘制文字
        drawRegion(canvas);//绘制区域
    }
    
##### (六)Region的使用：遥控器界面的绘制
![遥控器](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/%E9%81%A5%E6%8E%A7%E5%99%A8.gif)<br>
实现原理：遥控器的上下左右键是不规则的，绘制使用的是Canvas的drawPath方法。对点击事件的触点位置的判断使用的是Region类的contains方法<br>
代码片段：<br>
onSizeChanged方法中片段<br>

        //根据视图大小，初始化Path和Region
        center_p.addCircle(0, 0, 0.2f * minWidth, Path.Direction.CW);
        center.setPath(center_p, globalRegion);

        right_p.addArc(bigCircle, -42, bigSweepAngle);
        right_p.arcTo(smallCircle, 40, smallSweepAngle);
        right_p.close();
        right.setPath(right_p, globalRegion);
onDraw方法中<br>

     @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        //获取测量矩阵(逆矩阵)
        if (mMapMatrix.isIdentity()) {
            canvas.getMatrix().invert(mMapMatrix);
        }

        CanvasAidUtils.set2DAxisLength(500, 500, 700, 500);
        CanvasAidUtils.setLineColor(Color.CYAN);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);

        canvas.drawPath(center_p, mPaint);
        canvas.drawPath(right_p, mPaint);
        canvas.drawPath(down_p, mPaint);
        canvas.drawPath(left_p, mPaint);
        canvas.drawPath(up_p, mPaint);

        //绘制触摸区域颜色
        mPaint.setColor(mTouchedColor);
        if (currentFlag == CENTER) {
            canvas.drawPath(center_p, mPaint);
        } else if (currentFlag == RIGHT) {
            canvas.drawPath(right_p, mPaint);
        } else if (currentFlag == DOWN) {
            canvas.drawPath(down_p, mPaint);
        } else if (currentFlag == LEFT) {
            canvas.drawPath(left_p, mPaint);
        } else if (currentFlag == UP) {
            canvas.drawPath(up_p, mPaint);
        }
        mPaint.setColor(mDefaultColor);
        }
    
onTouchEvent方法中片段<br>

    case MotionEvent.ACTION_UP:
                currentFlag = getTouchedPath(x, y);
                //如果手指按下区域和抬起区域相同且不为空,则为点击事件
                if (currentFlag == touchFlag && currentFlag != -1 && mListener != null) {
                    if (currentFlag == CENTER) {
                        mListener.onCenterClicked();
                    } else if (currentFlag == RIGHT) {
                        mListener.onRightClicked();
                    } else if (currentFlag == DOWN) {
                        mListener.onDownClicked();
                    } else if (currentFlag == LEFT) {
                        mListener.onLeftClicked();
                    } else if (currentFlag == UP) {
                        mListener.onUpClicked();
                    }
                }
                touchFlag = currentFlag = -1;
                break;
                
##### (七)PathMeasure类的使用：一个SearchView
PathMeasure是一个用来测量Path的类，主要有以下方法：<br>
![PathMeasure类方法索引截图](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/PathMeasureMethods.png)<br>
![SearchView](https://github.com/liuhuan2015/CanvasLearn2/blob/master/app/images/SearchView.gif)<br>
实现原理：<br>
* 1.整个动画效果用了两个Path，一个是中间放大镜，一个是外侧的圆环，其中Path的走向要把握好<br>
* 2.Path上面的点是使用的PathMeasure进行的测量，无需计算。使用PathMeasure对Path进行截取。<br>
* 3.动画效果使用了ValueAnimator，它可以将一段时间映射到一段数值上，随着时间的变化不断的更新数值，并且可以使用插值器来控制数值变化规律<br>
* 4.具体的绘制是根据当前状态以及从ValueAnimator获得的数值来截取Path中合适的部分绘制出来<br>
代码片段：<br>
构造方法：<br>

        public PathMeasureSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

        initPath();

        initListener();

        initHandler();

        initAnimator();

        //进入开始动画
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
        }
    
onDraw方法中：<br>

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawColor(Color.parseColor("#0082D7"));

        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(path_search, mPaint);
                break;
            case STARTING:
                pathMeasure.setPath(path_search, false);
                Path dst = new Path();
                pathMeasure.getSegment(pathMeasure.getLength() * mAnimatorValue,
                        pathMeasure.getLength(), dst, true);//true，保证截取出来的片段不会发生形变
                canvas.drawPath(dst, mPaint);
                break;
            case SEARCHING:
                pathMeasure.setPath(path_circle, false);
                Path dst2 = new Path();
                float stop = pathMeasure.getLength() * mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
                //float start = (float) (stop - (Math.abs(mAnimatorValue - 0.5) * 200f));
                pathMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;
            case ENDING:
                pathMeasure.setPath(path_search, true);
                Path dst3 = new Path();
                pathMeasure.getSegment(pathMeasure.getLength() * mAnimatorValue, pathMeasure.getLength(),
                        dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
            }
        }




