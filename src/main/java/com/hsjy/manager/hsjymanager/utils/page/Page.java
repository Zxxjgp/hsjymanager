/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.hsjy.manager.hsjymanager.utils.page;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hsjy.manager.hsjymanager.utils.WebUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 分页类
 *
 * @param <E>
 * @author ThinkGem
 * @version 2013-7-2
 */
@AllArgsConstructor
public class Page<E> extends Pagination {
    /**
     * 当前页码
     */
    private Integer pageNo = 1;

    /**
     * 页面大小，设置为“-1”表示不进行分页（分页无效）
     */
    private Integer pageSize = 10;


    /**
     * 总记录数，设置为“-1”表示不查询总数
     */
    private Integer count;

    /**
     * 首页索引
     */
    private int first;
    /**
     * 尾页索引
     */
    private int last;
    /**
     * 上一页索引
     */
    @JSONField(serialize = false)
    private int prev;
    /**
     * 下一页索引
     */
    @JSONField(serialize = false)
    private int next;
    /**
     * 是否是第一页
     */
    @JSONField(serialize = false)
    private boolean firstPage;
    /**
     * 是否是最后一页
     */
    private boolean lastPage;

    private List<E> list = new ArrayList();

    /**
     * 标准查询有效， 实例： updatedate desc, name asc
     */
    private String orderBy = ""; //


    private boolean enalbed = true;

    private boolean hasNext = true;

    private boolean selectCount = false;


    public Page() {
        this(WebUtil.getHttpServletRequest(), 10);
    }

    public boolean isEnalbed() {
        return enalbed;
    }

    public void setEnalbed(boolean enalbed) {
        this.enalbed = enalbed;
    }

    /**
     * 构造方法
     *
     * @param request 传递 repage 参数，来记住页码
     */
    public Page(HttpServletRequest request) {
        this(request, -2);
    }

    /**
     * 构造方法
     *
     * @param request         传递 repage 参数，来记住页码
     * @param defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
     */
    public Page(HttpServletRequest request, int defaultPageSize) {
        if (request == null) {
            return;
        }
        // 设置页码参数（传递repage参数，来记住页码）
        String no = request.getParameter("pageNo");
        String hasNext = request.getParameter("hasNext");
        String enalbed = request.getParameter("enalbed");
        String selectCount = request.getParameter("selectCount");
        if (StringUtils.isEmpty(no)) {
            no = "1";
        }
        if (StringUtils.isNotEmpty(hasNext) && StringUtils.equals(hasNext, "false")) {
            this.setHasNext(false);
        }

        if (StringUtils.isNotEmpty(selectCount) && StringUtils.equals(selectCount, "true")) {
            this.setSelectCount(true);
        }
        if (StringUtils.isNotEmpty(enalbed) && StringUtils.equals(enalbed, "false")) {
            this.setEnalbed(false);
        }
        // 设置页面大小参数（传递repage参数，来记住页码大小）
        String size = request.getParameter("pageSize");
        if (StringUtils.isEmpty(size)) {
            size = "10";
        }
        if (defaultPageSize != -2) {
            this.pageSize = defaultPageSize;
        }
        // 设置排序参数
        String orderBy = request.getParameter("orderBy");
        if (StringUtils.isNotBlank(orderBy)) {
            this.setOrderBy(orderBy);
        }
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     */
    public Page(int pageNo, int pageSize) {
        this(pageNo, pageSize, 0);
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     */
    public Page(int pageNo, int pageSize, Integer count) {
        this(pageNo, pageSize, count, new ArrayList<E>());
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     * @param list     本页数据对象列表
     */
    public Page(int pageNo, int pageSize, Integer count, List<E> list) {
        this.setCount(count);
        this.setPageNo(pageNo);
        this.pageSize = pageSize;
        this.list = list;
    }

    /**
     * 初始化参数
     */
    public void initialize() {

        //1
        this.first = 1;

        this.last = (int) (count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

        if (this.count % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        if (this.pageNo <= 1) {
            this.pageNo = this.first;
            this.firstPage = true;
        }

        if (this.pageNo >= this.last) {
            this.pageNo = this.last;
            this.lastPage = true;
        }

        if (this.pageNo < this.last - 1) {
            this.next = this.pageNo + 1;
        } else {
            this.next = this.last;
        }

        if (this.pageNo > 1) {
            this.prev = this.pageNo - 1;
        } else {
            this.prev = this.first;
        }

        //2
        if (this.pageNo < this.first) {// 如果当前页小于首页
            this.pageNo = this.first;
        }

        if (this.pageNo > this.last) {// 如果当前页大于尾页
            this.pageNo = this.last;
        }



    }


    /**
     * 获取设置总数
     *
     * @return
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置数据总数
     *
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
        if (pageSize >= count) {
            pageNo = 1;
        }
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码
     *
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        this.setCurrent(pageNo);
    }

    /**
     * 获取页面大小
     *
     * @return
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面大小（最大500）
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;// > 500 ? 500 : pageSize;
        this.setSize(this.pageSize);
    }

    /**
     * 首页索引
     *
     * @return
     */

    public int getFirst() {
        return first;
    }

    /**
     * 尾页索引
     *
     * @return
     */

    public int getLast() {
        return last;
    }

    /**
     * 获取页面总数
     *
     * @return getLast();
     */

    public int getTotalPage() {
        return getLast();
    }

    /**
     * 是否为第一页
     *
     * @return
     */

    public boolean isFirstPage() {
        return firstPage;
    }

    /**
     * 是否为最后一页
     *
     * @return
     */

    public boolean isLastPage() {
        return lastPage;
    }

    /**
     * 上一页索引值
     *
     * @return
     */

    public int getPrev() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    /**
     * 下一页索引值
     *
     * @return
     */

    public int getNext() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    /**
     * 获取本页数据对象列表
     *
     * @return List<T>
     */
    public List<E> getList() {
        return list;
    }

    /**
     * 设置本页数据对象列表
     *
     * @param list
     */
    public void setList(List list) {
        this.list = list;
        if (isEnalbed()) {
            initialize();
        }
    }



    /**
     * 获取查询排序字符串
     *
     * @return
     */

    @JSONField(serialize = false)
    public String getOrderBy() {
        // SQL过滤，防止注入
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(orderBy).find()) {
            return "";
        }
        return orderBy;
    }
    /**
     * 设置查询排序，标准查询有效， 实例： updatedate desc, name asc
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * 分页是否有效
     *
     * @return this.pageSize==-1
     */
    @JSONField(serialize = false)
    public boolean isDisabled() {
        return this.pageSize == -1;
    }

    /**
     * 是否进行总数统计
     *
     * @return this.count==-1
     */

    @JSONField(serialize = false)
    public boolean isNotCount() {
        return this.count == -1;
    }


    /**
     * 获取 Hibernate MaxResults
     */
    @JSONField(serialize = false)
    public int getMaxResults() {
        return getPageSize();
    }


    public Page list(List<?> list) {
        setList(list);
        return this;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isSelectCount() {
        return selectCount;
    }

    public void setSelectCount(boolean selectCount) {
        this.selectCount = selectCount;
    }


    @JSONField(serialize = false)
    private int total;
    @JSONField(serialize = false)
    private int size = 10;
    @JSONField(serialize = false)
    private int pages;
    @JSONField(serialize = false)
    private int current = 1;
    @JSONField(serialize = false)
    private boolean searchCount = true;

    /**
     * 开启排序（默认 true） 只在代码逻辑判断 并不截取sql分析
     *
     * @see com.baomidou.mybatisplus.mapper.SqlHelper fillWrapper
     **/
    @JSONField(serialize = false)
    private boolean openSort = true;

    /**
     * <p>
     * SQL 排序 ORDER BY 字段，例如： id DESC（根据id倒序查询）
     * </p>
     * <p>
     * DESC 表示按倒序排序(即：从大到小排序)<br>
     * ASC 表示按正序排序(即：从小到大排序)
     * </p>
     */
    @JSONField(serialize = false)
    private String orderByField;

    /**
     * 是否为升序 ASC（ 默认： true ）
     */
    @JSONField(serialize = false)
    private boolean isAsc = true;


    @Override
    @JSONField(serialize = false)
    public boolean hasPrevious() {
        return this.current > 1;
    }

    @Override
    @JSONField(serialize = false)
    public boolean hasNext() {
        return this.current < this.pages;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public Pagination setTotal(int total) {
        this.total = total;
        return this;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Pagination setSize(int size) {
        this.size = size;
        return this;
    }

    @Override
    public int getPages() {
        if (this.size == 0) {
            return 0;
        }
        this.pages = this.total / this.size;
        if (this.total % this.size != 0) {
            this.pages++;
        }
        return this.pages;
    }

    @Override
    public int getCurrent() {
        return current;
    }

    @Override
    public Pagination setCurrent(int current) {
        this.current = current;
        return this;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isSearchCount() {
        return searchCount;
    }

    @Override
    public Pagination setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    @Override
    public String getOrderByField() {
        return orderByField;
    }

    @Override
    public Pagination setOrderByField(String orderByField) {
        if (com.baomidou.mybatisplus.toolkit.StringUtils.isNotEmpty(orderByField)) {
            this.orderByField = orderByField;
        }
        return this;
    }

    @Override
    public boolean isOpenSort() {
        return openSort;
    }

    @Override
    public Pagination setOpenSort(boolean openSort) {
        this.openSort = openSort;
        return this;
    }

    @Override
    public String toString() {
        return "Pagination { total=" + total + " ,size=" + size + " ,pages=" + pages + " ,current=" + current + " }";
    }


}
