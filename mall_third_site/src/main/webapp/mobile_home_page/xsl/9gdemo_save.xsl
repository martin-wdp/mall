<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- 定义xml样式 -->
	<xsl:template match="/">
	<html>
	<head>
		<title>移动版首页</title>
		<link href="../css/bootstrap.min.css" rel="stylesheet"/>
    	<link href="../css/style.css" rel="stylesheet"/>
	</head>
	<body>
		<div id="ip_cont" class="ip_cont">
			<xsl:apply-templates />
		</div>
	</body>
	</html>
	</xsl:template>

	<xsl:template match="app_cont">
		<div class="app_item app_cube" style="display: block;">
			<div class="app_cont" style="height: 320px;">
				<xsl:attribute name="id">app_cont<xsl:value-of
					select="@id" /></xsl:attribute>
				<xsl:apply-templates select="a" />
			</div>
		</div>
	</xsl:template>
	<xsl:template match="img_adv">
		<div class="app_item app_cube" style="display: block;">
			<div class="img_adv item">
        		<xsl:attribute name="id">img_adv_<xsl:value-of select="@id" /></xsl:attribute>
				<xsl:apply-templates select="a"/>
	        </div>
		</div>
	</xsl:template>
	
	<xsl:template match="roll_adv">
		<div class="app_item app_cube" style="display: block;">
			<div class="roll_adv item">
        		<xsl:attribute name="id">roll_adv<xsl:value-of select="@id" /></xsl:attribute>
				<div name="roll_banner" class="roll_banner">
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<xsl:apply-templates select="roll_a"/>
						</div>
						<div class="swiper-pagination"></div>
					</div>
				</div>
        	</div>
        </div>
	</xsl:template>
	
	<xsl:template match="roll_a">
		<div class="swiper-slide">
		<a href="#">
			<xsl:attribute name="href">
				<xsl:value-of select="@href" />
			</xsl:attribute>
			<xsl:apply-templates select="img"/>
		</a>
		</div>
	</xsl:template>

	<xsl:template match="a">
		<a href="#">
			<xsl:attribute name="href">
				<xsl:value-of select="@href" />
			</xsl:attribute>
			<xsl:apply-templates select="img" />
		</a>
	</xsl:template>
	<xsl:template match="img">
		<img>
			<xsl:attribute name="id">
				<xsl:value-of select="@id" />
			</xsl:attribute>
			<xsl:attribute name="src">
				<xsl:value-of select="@src" />
			</xsl:attribute>
			<xsl:attribute name="width">
				<xsl:value-of select="@width" />
			</xsl:attribute>
			<xsl:attribute name="height">
				<xsl:value-of select="@height" />
			</xsl:attribute>
			<xsl:attribute name="class">
				<xsl:value-of select="@class" />
			</xsl:attribute>
			<xsl:attribute name="style">
				<xsl:value-of select="@style" />
			</xsl:attribute>
		</img>
	</xsl:template>
</xsl:stylesheet>

