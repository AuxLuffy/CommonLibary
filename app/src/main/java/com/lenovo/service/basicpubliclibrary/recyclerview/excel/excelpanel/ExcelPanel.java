package com.lenovo.service.basicpubliclibrary.recyclerview.excel.excelpanel;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.excel.utils.Utils;

import java.util.Map;
import java.util.TreeMap;




public class ExcelPanel extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    public static final int DEFAULT_LENGTH = 56;
    public static final int LOADING_VIEW_WIDTH = 30;

    private int leftCellWidth;
    private int topCellHeight;
    private int normalCellWidth;
    private int loadingViewWidth;
    private int amountAxisX = 0;
    private int amountAxisY = 0;
    private int dividerHeight;
    private boolean hasHeader;
    private boolean hasFooter;
    private boolean dividerLineVisible;

    protected View dividerLine;
    protected RecyclerView mRecyclerView;
    protected RecyclerView topRecyclerView;
    protected RecyclerView leftRecyclerView;
    protected BaseExcelPanelAdapter excelPanelAdapter;
    private static Map<Integer, Integer> indexHeight;
    private static Map<Integer, Integer> indexWidth;

    private OnLoadMoreListener onLoadMoreListener;

    public interface OnLoadMoreListener {
        /**
         * when the loading icon appeared, this method may be called many times
         */
        void onLoadMore();

        /**
         * when the loading icon appeared, this method may be called many times. The excelPanel will dislocation
         * when the data have been added, you must call {@link #addHistorySize(int) addHistorySize(int)}.
         */
        void onLoadHistory();
    }

    public ExcelPanel(Context context) {
        this(context, null);
    }

    public ExcelPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ExcelPanel,
                0, 0);
        try {
            leftCellWidth = (int) a.getDimension(R.styleable.ExcelPanel_left_cell_width, Utils.dp2px(DEFAULT_LENGTH, getContext()));
            topCellHeight = (int) a.getDimension(R.styleable.ExcelPanel_top_cell_height, Utils.dp2px(DEFAULT_LENGTH, getContext()));
            normalCellWidth = (int) a.getDimension(R.styleable.ExcelPanel_normal_cell_width, Utils.dp2px(DEFAULT_LENGTH, getContext()));
        } finally {
            a.recycle();
        }
        indexHeight = new TreeMap<>();
        indexWidth = new TreeMap<>();
        loadingViewWidth = Utils.dp2px(LOADING_VIEW_WIDTH, getContext());
        initWidget();
    }

    private void initWidget() {

        //content's RecyclerView
        mRecyclerView = createMajorContent();
        addView(mRecyclerView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LayoutParams mlp = (LayoutParams) mRecyclerView.getLayoutParams();
        mlp.leftMargin = leftCellWidth;
        mlp.topMargin = topCellHeight;
        mRecyclerView.setLayoutParams(mlp);

        //top RecyclerView
        topRecyclerView = createTopHeader();
        addView(topRecyclerView, new LayoutParams(LayoutParams.WRAP_CONTENT, topCellHeight));
        LayoutParams tlp = (LayoutParams) topRecyclerView.getLayoutParams();
        tlp.leftMargin = leftCellWidth;
        topRecyclerView.setLayoutParams(tlp);

        //left RecyclerView
        leftRecyclerView = createLeftHeader();
        addView(leftRecyclerView, new LayoutParams(leftCellWidth, LayoutParams.WRAP_CONTENT));
        LayoutParams llp = (LayoutParams) leftRecyclerView.getLayoutParams();
        llp.topMargin = topCellHeight;
        leftRecyclerView.setLayoutParams(llp);

        dividerLine = createDividerToLeftHeader();
        addView(dividerLine, new ViewGroup.LayoutParams(1, ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutParams lineLp = (LayoutParams) dividerLine.getLayoutParams();
        lineLp.leftMargin = leftCellWidth;
        dividerLine.setLayoutParams(lineLp);
        getViewTreeObserver().addOnGlobalLayoutListener(this);

    }

    @Override
    public void onGlobalLayout() {
        if (getMeasuredHeight() != dividerHeight) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        LayoutParams lineLp1 = (LayoutParams) dividerLine.getLayoutParams();
        dividerHeight = lineLp1.height = getMeasuredHeight();
        dividerLine.setLayoutParams(lineLp1);
    }

    protected RecyclerView createTopHeader() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(getTopLayoutManager());
        recyclerView.addOnScrollListener(contentScrollListener);
        return recyclerView;
    }

    protected RecyclerView createLeftHeader() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(getLeftLayoutManager());
        recyclerView.addOnScrollListener(leftScrollListener);
        return recyclerView;
    }

    protected RecyclerView createMajorContent() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addOnScrollListener(contentScrollListener);
        return recyclerView;
    }

    protected View createDividerToLeftHeader() {
        View view = new View(getContext());
        view.setVisibility(GONE);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bg_line));
        return view;
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        if (null == mRecyclerView || null == mRecyclerView.getLayoutManager()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            return layoutManager;
        }
        return mRecyclerView.getLayoutManager();
    }

    private RecyclerView.LayoutManager getTopLayoutManager() {
        if (null == topRecyclerView || null == topRecyclerView.getLayoutManager()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            return layoutManager;
        }
        return topRecyclerView.getLayoutManager();
    }

    private RecyclerView.LayoutManager getLeftLayoutManager() {
        if (null == leftRecyclerView || null == leftRecyclerView.getLayoutManager()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            return layoutManager;
        }
        return leftRecyclerView.getLayoutManager();
    }

    /**
     * horizontal listener
     */
    private RecyclerView.OnScrollListener contentScrollListener
            = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            amountAxisX += dx;
            fastScrollTo(amountAxisX, mRecyclerView, loadingViewWidth, hasHeader);
            fastScrollTo(amountAxisX, topRecyclerView, loadingViewWidth, hasHeader);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = manager.getItemCount();
            int firstVisibleItem = manager.findFirstVisibleItemPosition();
            if (totalItemCount - visibleItemCount <= firstVisibleItem && onLoadMoreListener != null && hasFooter) {
                onLoadMoreListener.onLoadMore();
            }
            if (amountAxisX < loadingViewWidth && onLoadMoreListener != null && hasHeader) {
                onLoadMoreListener.onLoadHistory();
            }
            if (((hasHeader && amountAxisX > loadingViewWidth) || (!hasHeader && amountAxisX > 0)) && dividerLineVisible) {
                dividerLine.setVisibility(VISIBLE);
            } else {
                dividerLine.setVisibility(GONE);
            }
        }
    };

    /**
     * vertical listener
     */
    private RecyclerView.OnScrollListener leftScrollListener
            = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            amountAxisY += dy;
            for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
                if (mRecyclerView.getChildAt(i) instanceof RecyclerView) {
                    RecyclerView recyclerView1 = (RecyclerView) mRecyclerView.getChildAt(i);
                    fastScrollVertical(amountAxisY, recyclerView1);
                }
            }
            fastScrollVertical(amountAxisY, leftRecyclerView);
            if (excelPanelAdapter != null) {
                excelPanelAdapter.setAmountAxisY(amountAxisY);
            }
        }
    };

    void fastScrollVerticalLeft() {
        fastScrollVertical(amountAxisY, leftRecyclerView);
    }

    static void fastScrollVertical(int amountAxis, RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //call this method the OnScrollListener's onScrolled will be called，but dx and dy always be zero.
        linearLayoutManager.scrollToPositionWithOffset(0, -amountAxis);
    }

    private void fastScrollTo(int amountAxis, RecyclerView recyclerView, int offset, boolean hasHeader) {
        int position = 0, width = normalCellWidth;
        if (amountAxis >= offset && hasHeader) {
            amountAxis -= offset;
            position++;
        }
        position += amountAxis / width;
        amountAxis %= width;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //call this method the OnScrollListener's onScrolled will be called，but dx and dy always be zero.
        linearLayoutManager.scrollToPositionWithOffset(position, -amountAxis);
    }

    public void setAdapter(BaseExcelPanelAdapter excelPanelAdapter) {
        if (excelPanelAdapter != null) {
            this.excelPanelAdapter = excelPanelAdapter;
            this.excelPanelAdapter.setLeftCellWidth(leftCellWidth);
            this.excelPanelAdapter.setTopCellHeight(topCellHeight);
            this.excelPanelAdapter.setOnScrollListener(leftScrollListener);
            this.excelPanelAdapter.setExcelPanel(this);
            distributeAdapter();
        }
    }

    private void distributeAdapter() {
        if (leftRecyclerView != null) {
            leftRecyclerView.setAdapter(excelPanelAdapter.getLeftRecyclerViewAdapter());
        }
        if (topRecyclerView != null) {
            topRecyclerView.setAdapter(excelPanelAdapter.getTopRecyclerViewAdapter());
        }
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(excelPanelAdapter.getmRecyclerViewAdapter());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (indexHeight == null) {
            indexHeight = new TreeMap<>();
        }
        if (indexWidth == null) {
            indexWidth = new TreeMap<>();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (indexWidth != null) {
            indexWidth.clear();
        }
        if (indexHeight != null) {
            indexHeight.clear();
        }
        indexHeight = null;
        indexWidth = null;
    }

    /**
     * @param dx horizontal distance to scroll
     */
    void scrollBy(int dx) {
        contentScrollListener.onScrolled(mRecyclerView, dx, 0);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public boolean canChildScrollUp() {
        return amountAxisY > 0;
    }

    public void reset() {
        if (excelPanelAdapter != null) {
            excelPanelAdapter.disableFooter();
            excelPanelAdapter.disableHeader();
        }
        if (indexHeight == null) {
            indexHeight = new TreeMap<>();
        }
        if (indexWidth == null) {
            indexWidth = new TreeMap<>();
        }
        indexHeight.clear();
        indexWidth.clear();
        amountAxisY = 0;
        amountAxisX = 0;
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void addHistorySize(int size) {
        if (size > 0) {
            contentScrollListener.onScrolled(topRecyclerView, normalCellWidth * size, 0);
        }
    }

    public int findFirstVisibleItemPosition() {
        int position = -1;
        if (mRecyclerView.getLayoutManager() != null && excelPanelAdapter != null) {
            LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (hasHeader) {
                return firstVisibleItem - 1;
            }
            return firstVisibleItem;
        }
        return position;
    }

    public void enableDividerLine(boolean visible) {
        dividerLineVisible = visible;
    }
}
