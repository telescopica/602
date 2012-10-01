from django.db import models


class OrderItem(models.Model):
    amount = models.IntegerField()
    discount = models.FloatField()
    SKU = models.CharField()
    item = models.ForeignKey('OrderItem')



class Item(models.Model):
    code = models.CharField()
    packagingId = models.IntegerField()
    SKU = models.CharField()
    parentSKU = models.CharField()
    masterSKU = models.CharField()
    thumbnailURL = models.CharField()
    pictureURL = models.CharField()



class CatalogItem(models.Model):
    oldPrice = models.FloatField()
    currentPrice = models.FloatField()
    SKU = models.CharField()
    item = models.ForeignKey('CatalogItem')



class Order(models.Model):
    orderNumber = models.CharField()
    status = models.IntegerField()
    placementDate = models.BigIntegerField()
    deliveryDate = models.BigIntegerField()
    eta = models.BigIntegerField()
    createdBy = models.IntegerField()
    clientId = models.IntegerField()
    lastEvent = models.ForeignKey('Order')
    events = models.ForeignKey('Order')
    items = models.ForeignKey('Order')



class Catalog(models.Model):
    clientId = models.IntegerField()
    items = models.ForeignKey('Catalog')



class OrderEvent(models.Model):
    description = models.CharField()
    time = models.BigIntegerField()
    lastStatus = models.IntegerField()
    status = models.IntegerField()
    modifiedBy = models.IntegerField()



