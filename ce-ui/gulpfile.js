var gulp = require('gulp');
var jshint = require('gulp-jshint');
var clean = require('gulp-clean');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var es = require('event-stream');

var inject         = require('gulp-inject');
var series         = require('stream-series');


gulp.task('clean', function(){
	return gulp.src('src/main/resources/static/dist/')
	.pipe(clean());
});

gulp.task('jshint',function(){
	return gulp.src('src/main/resources/static/js/**/*.js')
	.pipe(jshint())
	.pipe(jshint.reporter('default'));
});

gulp.task('concat',['clean'], function(){
	return gulp.src('src/main/resources/static/js/**/*.js')
	.pipe(concat('all.min.js'))
	.pipe(gulp.dest('src/main/resources/static/dist/js'));
});


gulp.task('uglify',['concat'], function(){
	return gulp.src('src/main/resources/static/dist/js/**/*.js')
	.pipe(uglify())
	.pipe(gulp.dest('src/main/resources/static/dist/js'));
});

gulp.task('htmlmin', function(){
	return gulp.src('views/*.html')
	.pipe(htmlmin({collapseWhitespace: true}))
	.pipe(gulp.dest('dist/view'));
});

gulp.task('index',['concat'], function () {
    var target = gulp.src("src/main/resources/static/index.html");
    var sources = gulp.src(['src/main/resources/static/dist/js/*.js'], {read: false});
    return target.pipe(inject(series(sources), {relative: true}))
        .pipe(gulp.dest('src/main/resources/static/'));
});

gulp.task('default',['jshint','uglify']);
gulp.task('dev',['jshint','index']);
