package com.example.controller;

import com.example.common.Result;
import com.example.entity.Goods;
import com.example.service.GoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for managing goods category information.
 * Provides endpoints for adding, deleting, updating, and querying goods entries.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * Add a new goods entry.
     *
     * @param goods The goods entity to be added.
     * @return A success result indicating the goods entry was added successfully.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Goods goods) {
        goodsService.add(goods);
        return Result.success();
    }

    /**
     * Delete a goods entry by its ID.
     *
     * @param id The ID of the goods entry to be deleted.
     * @return A success result indicating the goods entry was deleted successfully.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        goodsService.deleteById(id);
        return Result.success();
    }

    /**
     * Delete multiple goods entries by their IDs.
     *
     * @param ids A list of IDs representing the goods entries to be deleted.
     * @return A success result indicating the goods entries were deleted successfully.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        goodsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update an existing goods entry by its ID.
     *
     * @param goods The updated goods entity.
     * @return A success result indicating the goods entry was updated successfully.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Goods goods) {
        goodsService.updateById(goods);
        return Result.success();
    }

    /**
     * Retrieve a goods entry by its ID.
     *
     * @param id The ID of the goods entry to retrieve.
     * @return A success result containing the retrieved goods entry.
     */
    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer id) {
        Goods goods = goodsService.selectById(id);
        return Result.success(goods);
    }

    /**
     * Retrieve the top 15 goods entries.
     *
     * @return A success result containing a list of the top 15 goods entries.
     */
    @GetMapping("/selectTop15")
    public Result selectTop15() {
        List<Goods> list = goodsService.selectTop15();
        return Result.success(list);
    }

    /**
     * Retrieve all goods entries that match the specified criteria.
     *
     * @param goods The goods entity containing the filter criteria.
     * @return A success result containing a list of matching goods entries.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Goods goods) {
        List<Goods> list = goodsService.selectAll(goods);
        return Result.success(list);
    }

    /**
     * Retrieve goods entries by type ID.
     *
     * @param id The ID of the type to filter goods by.
     * @return A success result containing a list of goods entries under the specified type.
     */
    @GetMapping("/selectByTypeId")
    public Result selectByTypeId(@RequestParam Integer id) {
        List<Goods> list = goodsService.selectByTypeId(id);
        return Result.success(list);
    }

    /**
     * Retrieve goods entries by name.
     *
     * @param name The name of the goods to filter by.
     * @return A success result containing a list of goods entries that match the specified name.
     */
    @GetMapping("/selectByName")
    public Result selectByName(@RequestParam String name) {
        List<Goods> list = goodsService.selectByName(name);
        return Result.success(list);
    }

    /**
     * Retrieve goods entries by business ID.
     *
     * @param id The ID of the business to filter goods by.
     * @return A success result containing a list of goods entries associated with the specified business.
     */
    @GetMapping("/selectByBusinessId")
    public Result selectByBusinessId(@RequestParam Integer id) {
        List<Goods> list = goodsService.selectByBusinessId(id);
        return Result.success(list);
    }

    /**
     * Recommend goods entries based on predefined logic.
     *
     * @return A success result containing a list of recommended goods entries.
     */
    @GetMapping("/recommend")
    public Result recommend() {
        List<Goods> list = goodsService.recommend();
        return Result.success(list);
    }

    /**
     * Retrieve goods entries with pagination support.
     *
     * @param goods The goods entity containing the filter criteria.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of items per page (default is 10).
     * @return A success result containing paginated goods data.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Goods goods,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Goods> page = goodsService.selectPage(goods, pageNum, pageSize);
        return Result.success(page);
    }
}
