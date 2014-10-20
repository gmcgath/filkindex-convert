<?xml version="1.0" encoding="utf-8"?>
<?xml-stylesheet href="stylesheets/MODS-HTML.xsl" type="text/xsl" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:mods="http://www.loc.gov/mods/v3" exclude-result-prefixes="mods">

  <xsl:output method="html" indent="yes"/>

  <xsl:param name="debug" select="''"/> <!-- Set this to 'true' to turn on warnings. -->
  
  <xsl:template match="mods:modsCollection">
    <xsl:apply-templates select="mods:mods"/>
  </xsl:template>

  <xsl:template match="mods:mods">
	<html>
	<head>
	<style type="text/css">
	  .note { font-size: 9pt; font-style: italic; color: blue}
	  .booktitle {font-family: sans-serif; font-size: 16pt; font-weight: bold }
	  .booksubtitle {font-family: sans-serif; font-size: 14pt; text-indent: 1cm}
	</style>
	<title>
    <xsl:apply-templates select="mods:titleInfo" mode="head"/>
    </title></head>
    <body bgcolor="#EEFFEE">
    <xsl:apply-templates select="mods:titleInfo" mode="book"/>
    <xsl:apply-templates select="mods:abstract" mode="book"/>
    <xsl:apply-templates select="mods:name"/>
    <xsl:apply-templates select="mods:originInfo" mode="book"/>
    <xsl:apply-templates select="mods:accessCondition" mode="book"/>
    <xsl:apply-templates select="mods:physicalDescription" mode="book"/>
    <xsl:apply-templates select="mods:identifier" mode="book"/>
    <ul>
      <xsl:apply-templates select="mods:relatedItem" mode="book"/>
    </ul>
    <xsl:apply-templates select="mods:note" mode="book"/>
    <xsl:apply-templates select="mods:recordInfo" mode="book"/>
    <hr/>
    <p><a href="index.html">The MASSFILC Filk Book Index</a></p>
    <p>Copyright 2006-2014 by MASSFILC, Inc.
    <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">
    <img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by/3.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative Commons Attribution 3.0 Unported License</a>.
    </p>
    </body>
    </html>
  </xsl:template>
    
  <!-- Templates for generating the HTML header -->
  <xsl:template match="mods:titleInfo" mode="head">
    <xsl:value-of select="mods:title" />
  </xsl:template>
  
  <!-- Templates for matching elements at mods (book) level -->
  <xsl:template match="mods:titleInfo" mode="book">
    <p>
    <div class="booktitle"><xsl:value-of select="mods:title"/></div>
    <xsl:if test="mods:subTitle">
      <br/>
      <div class="booksubtitle"><xsl:value-of select="mods:subTitle"/></div>
    </xsl:if>
    </p>
  </xsl:template>
  
  <xsl:template match="mods:edition" mode="book">
    <xsl:text> (</xsl:text>
    <xsl:value-of select="."/>
    <xsl:text>) </xsl:text>
  </xsl:template>
  
  <xsl:template match="mods:abstract" mode="book">
    <i><xsl:value-of select="."/></i><br/>
  </xsl:template>
  
  <xsl:template match="mods:note[@type='ownership']" mode="book">
    <p class='note'><i><xsl:value-of select="."/></i></p>
  </xsl:template>

  <xsl:template match="mods:recordInfo" mode="book">
    <p class='note'><i>
    <xsl:text>Record created: </xsl:text>
    <xsl:value-of select="mods:recordCreationDate"/></i></p>
    <p class='note'><i>
    <xsl:text>Record updated: </xsl:text>
    <xsl:value-of select="mods:recordChangeDate"/></i></p>
  </xsl:template>
  
  <xsl:template match="mods:originInfo" mode="book">
    <i><xsl:text>Publisher: </xsl:text></i>
       <xsl:for-each select="mods:publisher">
         <xsl:value-of select="."/> 
         <xsl:if test="position()!=last()">
           <xsl:text>, </xsl:text>
         </xsl:if>
       </xsl:for-each>
    <xsl:text>, </xsl:text>
    <xsl:apply-templates select="mods:dateIssued" mode="book"/>
    <xsl:apply-templates select="mods:edition" mode="book"/>
    <xsl:if test="mods:note">
    <br/>
    <i><xsl:text>Publication note: </xsl:text></i>
      <xsl:value-of select="mods:note"/>
    </xsl:if>
    <br/>
  </xsl:template>
  
  <xsl:template match="mods:publisher" mode="book">
    <xsl:value-of select="."/>
  </xsl:template>
  
  <xsl:template match="mods:dateIssued" mode="book">
    <xsl:value-of select="."/>
  </xsl:template>
  
  <xsl:template match="mods:accessCondition" mode="book">
    <i><xsl:text>Rights: </xsl:text></i>
    <xsl:value-of select="."/>
    <br/>
  </xsl:template>
  
  <xsl:template match="mods:physicalDescription" mode="book">
    <xsl:if test="mods:note">
      <i><xsl:text>Physical info: </xsl:text></i>
      <xsl:value-of select="mods:note"/>
      <br/>
    </xsl:if>
    <xsl:if test="mods:extent">
      <i><xsl:text>Extent: </xsl:text></i>
      <xsl:value-of select="mods:extent"/>
      <br/>
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="mods:relatedItem" mode="book">
    <xsl:apply-templates mode="song"/>
  </xsl:template>
  
  <xsl:template match="mods:identifier" mode="book">
    <i><xsl:value-of 
	  select="translate(@type,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')"/>
      <xsl:text>: </xsl:text></i>
    <xsl:value-of select="."/>
    <br/>
  </xsl:template>

  <xsl:template match="mods:note" mode="book">
      <i><xsl:text>Note: </xsl:text></i>
      <xsl:value-of select="."/>
  </xsl:template>
  
  <!-- Templates for matching elements at top relatedItem (song) level -->
  <xsl:template match="mods:titleInfo" mode="song">
    <li>
      <b><xsl:value-of select="mods:title"/></b>
      <xsl:if test="mods:subTitle">
        <xsl:text>: </xsl:text>
        <xsl:value-of select="mods:subTitle"/>
      </xsl:if>
    </li>
  </xsl:template>
  <xsl:template match="mods:name" mode="song">
    <xsl:apply-templates select="."/>
  </xsl:template>
  
  <xsl:template match="mods:language" mode="song">
    <!-- This test covers the most likely languages, other than English.
         Feel free to add ISO 639-2b codes. -->
    <xsl:if test="contains(mods:languageTerm,'deu') or contains(mods:languageTerm,'ger')">
      <i><xsl:text>In German</xsl:text></i>
      <br/>
    </xsl:if>
    <xsl:if test="contains(mods:languageTerm,'fre') or contains(mods:languageTerm,'fra')">
      <i><xsl:text>In French</xsl:text></i>
      <br/>
    </xsl:if>
    <xsl:if test="contains(mods:languageTerm,'heb')">
      <i><xsl:text>In Hebrew</xsl:text></i>
      <br/>
    </xsl:if>
    <xsl:if test="contains(mods:languageTerm,'tlh')">
      <i><xsl:text>In Klingon</xsl:text></i>
      <br/>
    </xsl:if>
    <xsl:if test="contains(mods:languageTerm,'lat')">
      <i><xsl:text>In Latin</xsl:text></i>
      <br/>
    </xsl:if>
    <xsl:if test="contains(mods:languageTerm,'swe')">
      <i><xsl:text>In Swedish</xsl:text></i>
      <br/>
    </xsl:if>
  </xsl:template>
  <xsl:template match="mods:part[mods:extent[@unit='pages']/mods:start]" mode="song">
    <xsl:text>p. </xsl:text><xsl:value-of select="mods:extent/mods:start"/>
  </xsl:template>
  
  <xsl:template match="mods:typeOfResource" mode="song">
    <xsl:if test="contains(.,'notated music')">
      <i><xsl:text>Music notation included</xsl:text></i>
      <br/>
    </xsl:if>
  </xsl:template>
  
  <!-- 
  <xsl:template match="mods:name" mode="song">
    <xsl:value-of select="mods:namePart"/>
       <xsl:text> (</xsl:text>
       <xsl:for-each select="mods:role/mods:roleTerm">
         <xsl:value-of select="."/> 
         <xsl:if test="position()!=last()">
           <xsl:text>, </xsl:text>
         </xsl:if>
       </xsl:for-each>
       <xsl:text>)</xsl:text>
       <br/>
  </xsl:template>
  -->

  <xsl:template match="mods:originInfo" mode="song">
    <i>
      <xsl:apply-templates select="mods:dateIssued" mode="song"/>
      <xsl:apply-templates select="mods:copyrightDate" mode="song"/>
    </i>
  </xsl:template>
  
  <xsl:template match="mods:dateIssued" mode="song">
    <xsl:text>Date issued:</xsl:text><xsl:value-of select="."/><br/>
  </xsl:template>

  <xsl:template match="mods:copyrightDate" mode="song">
    <xsl:text>Copyright </xsl:text><xsl:value-of select="."/><br/>
  </xsl:template>

  <xsl:template match="mods:relatedItem" mode="song">
      <i><xsl:text>Based on: &quot;</xsl:text></i>
      <xsl:value-of select="mods:titleInfo/mods:title"/>
      <xsl:text>&quot;</xsl:text> 
      <xsl:for-each select="mods:name">

         <xsl:if test="position()=1">
           <xsl:text> by </xsl:text>
         </xsl:if>
         <xsl:choose>
           <xsl:when test="@type='personal' and contains(mods:namePart, ', ')">
               <xsl:value-of select="concat(substring-after(mods:namePart, ', '),' ',substring-before(mods:namePart, ','))"/>
           </xsl:when>
           <xsl:otherwise>
               <xsl:value-of select="mods:namePart"/>
           </xsl:otherwise>
         </xsl:choose>
         <xsl:if test="position()!=last()">
           <xsl:text> and </xsl:text>
         </xsl:if>
      </xsl:for-each>
      <xsl:if test="mods:note">
        <xsl:text> (</xsl:text>
        <xsl:value-of select="mods:note"/>
        <xsl:text>)</xsl:text>
      </xsl:if>
      <br/>
  </xsl:template>
  
  <xsl:template match="mods:note" mode="song">
      <i><xsl:text>Note: </xsl:text></i>
      <xsl:value-of select="."/>
      <br/>
  </xsl:template>

  <xsl:template match="mods:genre" mode="song"> 
      <i><xsl:text>Genre: </xsl:text></i>
      <xsl:value-of select="."/>
      <br/>
  </xsl:template>
    
<!-- General templates, apply for both book and song. -->
  <xsl:template match="mods:name">
    <xsl:choose>
      <xsl:when test="@type='personal' and contains(mods:namePart, ', ')">
        <xsl:value-of select="concat(substring-after(mods:namePart, ', '),' ',substring-before(mods:namePart, ','))"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="mods:namePart"/>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:if test="mods:description">
      <xsl:text> &quot;</xsl:text>
      <xsl:value-of select="mods:description"/>
      <xsl:text>&quot;</xsl:text>
    </xsl:if>
    <xsl:text> (</xsl:text>
    <xsl:for-each select="mods:role/mods:roleTerm">
      <xsl:value-of select="."/> 
      <xsl:if test="position()!=last()">
        <xsl:text>, </xsl:text>
      </xsl:if>
    </xsl:for-each>
    <xsl:text>)</xsl:text>
    <br/> 
  </xsl:template>

  <xsl:template match="*">
    <xsl:if test="$debug='true'">
      <p>
	<big>ERROR!</big><br/><xsl:value-of select="."/>
      </p>
    </xsl:if>
  </xsl:template>

  <xsl:template match="*" mode="book">
    <xsl:if test="$debug='true'">
      <p>
	<big>ERROR in book mode!</big><br/>
	<xsl:value-of select="name()"/>::
	<xsl:value-of select="."/>
      </p>
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="*" mode="song">
    <xsl:if test="$debug='true'">
      <p>
	<big>ERROR in song mode!</big><br/><xsl:value-of select="."/>
      </p>
    </xsl:if>
  </xsl:template>
  
</xsl:stylesheet>
