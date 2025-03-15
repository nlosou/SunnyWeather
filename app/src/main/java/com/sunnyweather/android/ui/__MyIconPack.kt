package com.sunnyweather.android.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.sunnyweather.android.ui.myiconpack.Alarm
import com.sunnyweather.android.ui.myiconpack.Car
import com.sunnyweather.android.ui.myiconpack.CheckCircle
import com.sunnyweather.android.ui.myiconpack.Clothes
import com.sunnyweather.android.ui.myiconpack.Cosmetic
import com.sunnyweather.android.ui.myiconpack.Drugs
import com.sunnyweather.android.ui.myiconpack.Image
import com.sunnyweather.android.ui.myiconpack.Leaf
import com.sunnyweather.android.ui.myiconpack.LetterT
import com.sunnyweather.android.ui.myiconpack.ListSelect
import com.sunnyweather.android.ui.myiconpack.MapPoint
import com.sunnyweather.android.ui.myiconpack.NavigationPointer
import com.sunnyweather.android.ui.myiconpack.PencilLine
import com.sunnyweather.android.ui.myiconpack.Point
import com.sunnyweather.android.ui.myiconpack.Radioupnp
import com.sunnyweather.android.ui.myiconpack.RedoLine
import com.sunnyweather.android.ui.myiconpack.SnowLine
import com.sunnyweather.android.ui.myiconpack.Sport
import com.sunnyweather.android.ui.myiconpack.TShirt
import com.sunnyweather.android.ui.myiconpack.Umbrella
import com.sunnyweather.android.ui.myiconpack.UndoLine
import com.sunnyweather.android.ui.myiconpack.Water
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Alarm, Car, CheckCircle, Clothes, Cosmetic, Drugs, Image, Leaf, LetterT,
        ListSelect, MapPoint, NavigationPointer, PencilLine, Point, Radioupnp, RedoLine, SnowLine,
        Sport, TShirt, Umbrella, UndoLine, Water)
    return __AllIcons!!
  }
