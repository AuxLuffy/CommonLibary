package com.lenovo.service.basicpubliclibrary.opengl;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by xuxiaowei on 2017/9/29.
 */

public class CubeRenderer implements GLSurfaceView.Renderer {
    public class MyCube {

        private IntBuffer vertexBuffer;
        private IntBuffer   colorBuffer;
        private ByteBuffer indexBuffer;

        public MyCube()
        {
            int one = 65535;
            //每个顶点的坐标值(x,y,z)
            int vertex[] = {
                    -one, -one, -one,
                    one, -one, -one,
                    one,  one, -one,
                    -one,  one, -one,
                    -one, -one,  one,
                    one, -one,  one,
                    one,  one,  one,
                    -one,  one,  one
            };
            //立方体每个顶点的颜色值，格式为RRGGBBAA
            int colors[] = {
                    0,    0,    0,    one,
                    one,  0,    0,    one,
                    one,  one,  0,    one,
                    0,    one,  0,    one,
                    0,    0,    one,  one,
                    one,  0,    one,  one,
                    one,  one,  one,  one,
                    0,    one,  one,  one
            };
            //三角形顶点绘制顺序
            byte index[] = {
                    0, 4, 5,    0, 5, 1,
                    1, 5, 6,    1, 6, 2,
                    2, 6, 7,    2, 7, 3,
                    3, 7, 4,    3, 4, 0,
                    4, 7, 6,    4, 6, 5,
                    3, 0, 1,    3, 1, 2
            };

            ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length*4);
            vbb.order(ByteOrder.nativeOrder());
            vertexBuffer = vbb.asIntBuffer();
            vertexBuffer.put(vertex);
            vertexBuffer.position(0);

            ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
            cbb.order(ByteOrder.nativeOrder());
            colorBuffer = cbb.asIntBuffer();
            colorBuffer.put(colors);
            colorBuffer.position(0);

            indexBuffer = ByteBuffer.allocateDirect(index.length);
            indexBuffer.put(index);
            indexBuffer.position(0);
        }

        public void draw(GL10 gl)
        {
            gl.glFrontFace(GL10.GL_CW);
            gl.glVertexPointer(3, GL10.GL_FIXED, 0, vertexBuffer);
            gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, indexBuffer);
        }

    }

    private MyCube myCube;
    private float roate;

    public CubeRenderer() {
        myCube = new MyCube();
    }

    public void onDrawFrame(GL10 gl) {
        // 填充屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 设置模型视景矩阵为当前操作矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 将坐标原点移动到屏幕中心
        gl.glLoadIdentity();
        // 移动坐标系
        gl.glTranslatef(0, 0, -6.0f);
        // 在Y轴方向旋转坐标系
        gl.glRotatef(roate, 0, 1, 0);
        // 在X轴方向旋转坐标系
        gl.glRotatef(roate * 0.25f, 1, 0, 0);

        gl.glRotatef(roate, 0, 0, 1);
        /////////////////////////////////////
        gl.glScalef(1.2f, 1.2f, 1.2f);//**********大小
        ///////////////////////////////////
        // 开启顶点坐标
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 开启颜色
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        myCube.draw(gl);
        roate += 0.3f;//********设置旋转角度的增量
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glClearColor(0.5F, 0.5F, 0.5F, 1.0F);
    }
}
