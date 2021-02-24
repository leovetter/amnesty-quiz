package com.amnesty.quizzamnesty.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.amnesty.quizzamnesty.R;

import rx.subjects.BehaviorSubject;

public class PersonView extends View {

    private boolean isTouched;
    private Float heightTouched;
    private boolean validate = false;
    public BehaviorSubject<Float> heightSubject = BehaviorSubject.create();
    public Context myContext;

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        myContext = context;
        this.heightSubject.onNext(context.getResources().getDimension(R.dimen.person_height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.drawPerson(canvas);

        if(this.isTouched  && !this.validate) {

            Paint background = new Paint();
            background.setColor(getResources().getColor(R.color.fillPerson));
            background.setStrokeWidth(4);
            background.setStyle(Paint.Style.FILL);

            this.fillPerson(canvas, background);
        }

        if(this.validate) {

            this.validatePerson(canvas);
        }
    }

    private void validatePerson(Canvas canvas) {

        if (!this.isTouched) {

            Paint background = new Paint();
            background.setColor(getResources().getColor(R.color.colorRed));
            background.setStrokeWidth(4);
            background.setStyle(Paint.Style.FILL);
            this.heightTouched = 0f;
            this.drawHead(canvas, background);

        } else if (this.heightTouched < 0) {

            Paint background = new Paint();
            background.setColor(getResources().getColor(R.color.colorRedGreen));
            background.setStrokeWidth(4);
            background.setStyle(Paint.Style.FILL);
            this.drawHead(canvas, background);
        } else {

            Paint background = new Paint();
            background.setColor(getResources().getColor(R.color.colorRedGreen));
            background.setStrokeWidth(4);
            background.setStyle(Paint.Style.FILL);
            this.fillPerson(canvas, background);
            this.fillValidatePerson(canvas);

        }
    }

    private void fillValidatePerson(Canvas canvas) {

        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.colorRed));
        background.setStrokeWidth(4);
        background.setStyle(Paint.Style.FILL);

        if (this.heightTouched < 95) {

            this.drawValidateHead(canvas,background);

        } else if (this.heightTouched < getHeight() / 2 - 30) {

            this.drawValidateAboveArms(canvas,background);

        } else if (this.heightTouched < getHeight() / 2 - 10) {

            this.drawValidateArms(canvas, background);

        } else if (this.heightTouched < getHeight() - 30) {

            this.drawValidateUnderArms(canvas, background);

        } else if (this.heightTouched < getHeight() - 5) {

            this.drawValidateLegs(canvas, background);
        }
    }

    private void fillPerson(Canvas canvas, Paint background) {

        if (this.heightTouched < 95) {

            this.drawHead(canvas,background);

        } else if (this.heightTouched < getHeight() / 2 - 30) {

            this.drawAboveArms(canvas,background);

        } else if (this.heightTouched < getHeight() / 2 - 10) {

            this.drawArms(canvas, background);

        } else if (this.heightTouched < getHeight() - 30) {

            this.drawUnderArms(canvas, background);

        } else if (this.heightTouched < getHeight() - 5) {

            this.drawLegs(canvas, background);
        }
    }

    private void drawValidateHead(Canvas canvas, Paint background) {

        float heightUsed = this.heightTouched;
        if(this.heightTouched < 0) heightUsed = 0;

        Path path1 = new Path();
        path1.addArc(new RectF(getWidth() / 2 - 45, 6, getWidth() / 2 + 45, 94), 270 - heightUsed * 1.8f,   360 + (heightUsed * 3.6f));
        path1.close();
        canvas.drawPath(path1, background);
    }

    private void drawHead(Canvas canvas, Paint background) {

        float heightUsed = this.heightTouched;
        if(this.heightTouched < 0) heightUsed = 0;

        Path path1 = new Path();
        path1.addArc(new RectF(getWidth() / 2 - 45, 6, getWidth() / 2 + 45, 94), 270 - heightUsed * 1.8f, -360 + (heightUsed * 3.6f));
        path1.close();
        canvas.drawPath(path1, background);

        Path path2 = new Path();
        path2.moveTo(getWidth() / 2 - 65, getHeight() - 5);
        path2.lineTo(getWidth() / 2 - 45, getHeight() - 5);
        path2.lineTo(getWidth() / 2, getHeight() - 30);
        path2.lineTo(getWidth() / 2 + 45, getHeight() - 5);
        path2.lineTo(getWidth() / 2 + 65, getHeight() - 5);
        path2.lineTo(getWidth() / 2 + 20, getHeight() - 30);
        path2.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 10);
        path2.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 10);
        path2.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 30);
        path2.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 30);
        path2.lineTo(getWidth() / 2 + 20, 95);
        path2.lineTo(getWidth() / 2 - 20, 95);
        path2.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 30);
        path2.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 30);
        path2.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 10);
        path2.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 10);
        path2.lineTo(getWidth() / 2 - 20, getHeight() - 30);
        path2.close();
        canvas.drawPath(path2, background);
}

    private void drawValidateAboveArms(Canvas canvas, Paint background) {

        canvas.drawCircle(getWidth() / 2, 50, 45, background);

        Path path = new Path();
        path.moveTo(getWidth() / 2 + 20, this.heightTouched);
        path.lineTo(getWidth() / 2 + 20, 95);
        path.lineTo(getWidth() / 2 - 20, 95);
        path.close();
        canvas.drawPath(path, background);
    }

    private void drawAboveArms(Canvas canvas, Paint background) {

        Path path = new Path();
        path.moveTo(getWidth() / 2 - 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 - 45, getHeight() - 5);
        path.lineTo(getWidth() / 2, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 45, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 20, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, this.heightTouched);
        path.lineTo(getWidth() / 2 - 20, this.heightTouched);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() - 30);
        path.close();
        canvas.drawPath(path, background);
    }

    private void drawValidateArms(Canvas canvas, Paint background) {

        canvas.drawCircle(getWidth() / 2, 50, 45, background);

        Path path = new Path();
        path.moveTo(getWidth() / 2 + 70, this.heightTouched);
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, 95);
        path.lineTo(getWidth() / 2 - 20, 95);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, this.heightTouched);
        path.close();
        canvas.drawPath(path, background);
    }

    private void drawArms(Canvas canvas, Paint background) {

        Path path = new Path();
        path.moveTo(getWidth() / 2 - 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 - 45, getHeight() - 5);
        path.lineTo(getWidth() / 2, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 45, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 20, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 + 70, this.heightTouched);
        path.lineTo(getWidth() / 2 - 70, this.heightTouched);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() - 30);
        path.close();
        canvas.drawPath(path, background);
    }

    private void drawValidateUnderArms(Canvas canvas, Paint background) {

        canvas.drawCircle(getWidth() / 2, 50, 45, background);

        Path path = new Path();
        path.moveTo(getWidth() / 2 + 20, this.heightTouched);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 -10 );
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2- 10);
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, 95);
        path.lineTo(getWidth() / 2 - 20, 95);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, this.heightTouched);
        path.close();
        canvas.drawPath(path, background);
    }

    private void drawUnderArms(Canvas canvas, Paint background) {

        Path path = new Path();
        path.moveTo(getWidth() / 2 - 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 - 45, getHeight() - 5);
        path.lineTo(getWidth() / 2, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 45, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 20, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 20, this.heightTouched);
        path.lineTo(getWidth() / 2 - 20, this.heightTouched);
        path.lineTo(getWidth() / 2 - 20, getHeight() - 30);
        path.close();
        canvas.drawPath(path, background);
    }

    private void drawValidateLegs(Canvas canvas, Paint background) {

        canvas.drawCircle(getWidth() / 2, 50, 45, background);

        Path path = new Path();
        path.moveTo((float) (this.heightTouched - 0.5555555555 * (getWidth()/2) - getHeight() + 30) / (float) -0.55555555, this.heightTouched);
        path.lineTo(getWidth() / 2, getHeight() - 30);
        path.lineTo( (float) (this.heightTouched + 0.5555555555 * (getWidth()/2) - getHeight() + 30) / (float) 0.55555555, this.heightTouched);
        path.lineTo((float) (this.heightTouched - getHeight() + 0.55555555 * (getWidth()/2) + 41.11111111111) / (float) 0.555555555, this.heightTouched);
        path.lineTo(getWidth() / 2 + 20, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 -10 );
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2- 10);
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, 95);
        path.lineTo(getWidth() / 2 - 20, 95);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() - 30);
        path.lineTo((float) (this.heightTouched - getHeight() - 0.55555555 * (getWidth()/2) + 41.11111111111) / (float) -0.555555555, this.heightTouched);
        path.close();
        canvas.drawPath(path, background);
    }

    private void drawLegs(Canvas canvas, Paint background) {

        Path path1 = new Path();
        path1.moveTo(getWidth() / 2 - 65, getHeight() - 5);
        path1.lineTo(getWidth() / 2 - 45, getHeight() - 5);
        path1.lineTo((float) (this.heightTouched - 0.5555555555 * (getWidth()/2) - getHeight() + 30) / (float) -0.55555555, this.heightTouched);
        path1.lineTo( (float) (this.heightTouched - getHeight() - 0.55555555 * (getWidth()/2) + 41.11111111111) / (float) -0.555555555, this.heightTouched);
        path1.close();
        canvas.drawPath(path1, background);

        Path path2 = new Path();
        path2.moveTo(getWidth() / 2 + 65, getHeight() - 5);
        path2.lineTo(getWidth() / 2 + 45, getHeight() - 5);
        path2.lineTo((float) (this.heightTouched + 0.5555555555 * (getWidth()/2) - getHeight() + 30) / (float) 0.55555555, this.heightTouched);
        path2.lineTo( (float) (this.heightTouched - getHeight() + 0.55555555 * (getWidth()/2) + 41.11111111111) / (float) 0.555555555, this.heightTouched);
        path2.close();
        canvas.drawPath(path2, background);
    }

    private void drawPerson(Canvas canvas) {

        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.colorSubmit));
        background.setStrokeWidth(4);
        background.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 2, 50, 45, background);

        Path path = new Path();
        path.moveTo(getWidth() / 2 - 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 - 45, getHeight() - 5);
        path.lineTo(getWidth() / 2, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 45, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 65, getHeight() - 5);
        path.lineTo(getWidth() / 2 + 20, getHeight() - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 -10 );
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2- 10);
        path.lineTo(getWidth() / 2 + 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 + 20, 95);
        path.lineTo(getWidth() / 2 - 20, 95);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 30);
        path.lineTo(getWidth() / 2 - 70, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() / 2 - 10);
        path.lineTo(getWidth() / 2 - 20, getHeight() - 30);
        path.close();
        canvas.drawPath(path, background);
    }

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean touched) {
        isTouched = touched;
    }

    public Float getHeightTouched() {
        return heightTouched;
    }

    public void setHeightTouched(Float heightTouched) {
        this.heightTouched = heightTouched;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.setTouched(true);
        this.setHeightTouched(event.getY());
        this.invalidate();
        if(event.getY() >= 0 && event.getY() <= getHeight()) {
            this.heightSubject.onNext(event.getY());
        } else if (event.getY() < 0) {
            this.heightSubject.onNext(0f);
        } else if (event.getY() > getHeight()) {
            this.heightSubject.onNext(myContext.getResources().getDimension(R.dimen.person_height));
        }

        return true;
    }
}
