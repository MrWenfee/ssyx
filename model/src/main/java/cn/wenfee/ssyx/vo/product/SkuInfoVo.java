package cn.wenfee.ssyx.vo.product;

import cn.wenfee.ssyx.model.product.SkuAttrValue;
import cn.wenfee.ssyx.model.product.SkuImage;
import cn.wenfee.ssyx.model.product.SkuInfo;
import cn.wenfee.ssyx.model.product.SkuPoster;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfoVo extends SkuInfo {

	@ApiModelProperty(value = "海报列表")
	private List<SkuPoster> skuPosterList;

	@ApiModelProperty(value = "属性值")
	private List<SkuAttrValue> skuAttrValueList;

	@ApiModelProperty(value = "图片")
	private List<SkuImage> skuImagesList;

}

